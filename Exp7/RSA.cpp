#include <iostream>
#include <inttypes.h>

int64_t modPow(int64_t base, int64_t exponent, int64_t modulus);
int64_t modInverse(int64_t e, int64_t phi);
void extendedGCD(int64_t a, int64_t b, int64_t* d, int64_t* x, int64_t* y);

int main() {
    int64_t p = 61;
    int64_t q = 53;
    int64_t n = p * q;
    int64_t e = 17;
    int64_t phi = (p - 1) * (q - 1);

    int64_t k = modInverse(e, phi);
    int64_t d = k;

    if (k == -1) {
        std::cout << "Modular multiplicative inverse of 'e' does not exist!" << std::endl;
        return 1;
    }

    int64_t msg = 20;
    std::cout << "Message data = " << msg << std::endl;

    int64_t c = modPow(msg, e, n);
    std::cout << "Encrypted data = " << c << std::endl;

    int64_t m = modPow(c, d, n);
    std::cout << "Original Message Sent = " << m << std::endl;

    return 0;
}

int64_t modPow(int64_t base, int64_t exponent, int64_t modulus) {
    int64_t result = 1;
    base = base % modulus;
    while (exponent > 0) {
        if (exponent % 2 == 1) {
            result = (result * base) % modulus;
        }
        base = (base * base) % modulus;
        exponent /= 2;
    }
    return result;
}

int64_t modInverse(int64_t e, int64_t phi) {
    int64_t d, x, y;
    extendedGCD(e, phi, &d, &x, &y);

    if (d != 1) {
        // Modular inverse does not exist
        return -1;
    }

    // Ensure 'd' is positive
    while (x < 0) {
        x += phi;
    }

    return x;
}

void extendedGCD(int64_t a, int64_t b, int64_t* d, int64_t* x, int64_t* y) {
    if (b == 0) {
        *d = a;
        *x = 1;
        *y = 0;
    } else {
        int64_t x1, y1;
        extendedGCD(b, a % b, d, &x1, &y1);
        *x = y1;
        *y = x1 - (a / b) * y1;
    }
}
