// #include <iostream>
// #include <string>
// #include <cstdlib>
// #include <cryptlib.h>
// #include <aes.h>
// #include <ccm.h>
// #include <osrng.h>
// #include <hex.h>
// #include <filters.h>
// #include <assert.h>

// using namespace std;
// using namespace CryptoPP;

// void encryptDecryptAES(const byte key[], const byte iv, const string& input, string& output, bool encrypt) {
//     try {
//         output.clear();
//         CBC_Mode<AES>::Encryption enc;
//         CBC_Mode<AES>::Decryption dec;
        
//         if (encrypt) {
//             enc.SetKeyWithIV(key, AES::DEFAULT_KEYLENGTH, iv);
//             StringSource(input, true, new StreamTransformationFilter(enc, new StringSink(output));
//         } else {
//             dec.SetKeyWithIV(key, AES::DEFAULT_KEYLENGTH, iv);
//             StringSource(input, true, new StreamTransformationFilter(dec, new StringSink(output));
//         }
//     } catch (const CryptoPP::Exception& e) {
//         cerr << e.what() << endl;
//         exit(1);
//     }
// }

// int main() {
//     AutoSeededRandomPool prng;
//     byte key[AES::DEFAULT_KEYLENGTH];
//     prng.GenerateBlock(key, sizeof(key));
//     byte iv[AES::BLOCKSIZE];
//     prng.GenerateBlock(iv, sizeof(iv));
//     string plain = "CBC Mode Test";
//     string cipher, recovered;

//     cout << "plain text: " << plain << endl;

//     // Encryption
//     encryptDecryptAES(key, iv, plain, cipher, true);

//     // Pretty print key
//     string encodedKey;
//     StringSource(key, sizeof(key), true, new HexEncoder(new StringSink(encodedKey));
//     cout << "key: " << encodedKey << endl;

//     // Pretty print iv
//     string encodedIV;
//     StringSource(iv, sizeof(iv), true, new HexEncoder(new StringSink(encodedIV));
//     cout << "iv: " << encodedIV << endl;

//     // Pretty print cipher text
//     string encodedCipher;
//     StringSource(cipher, true, new HexEncoder(new StringSink(encodedCipher));
//     cout << "cipher text: " << encodedCipher << endl;

//     // Decryption
//     encryptDecryptAES(key, iv, cipher, recovered, false);
//     cout << "recovered text: " << recovered << endl;

//     return 0;
// }
