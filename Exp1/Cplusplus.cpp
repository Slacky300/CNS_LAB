#include <iostream>
using namespace std;

string encrypt(string m, int k)
{
    string encryp = "";
    for (int i = 0; i < m.length(); i++)
    {
        if (isupper(m[i]))
        {
            // For uppercase
            encryp += char(int(m[i] + k - 65) % 26 + 65);
        }
        else if (islower(m[i]))
        {
            // For lowercase
            encryp += char(int(m[i] + k - 97) % 26 + 97);
        }
        else
        {
            // For non-alphabetic characters, just append them as they are
            encryp += m[i];
        }
    }
    return encryp;
}

string decrypt(string newmessage, int k)
{
    string decrypt = "";
    for (int i = 0; i < newmessage.length(); i++)
    {
        if (isupper(newmessage[i]))
        {
            // For uppercase
            decrypt += char(int(newmessage[i] - k - 65 + 26) % 26 + 65);
        }
        else if (islower(newmessage[i]))
        {
            // For lowercase
            decrypt += char(int(newmessage[i] - k - 97 + 26) % 26 + 97);
        }
        else
        {
            // For non-alphabetic characters, just append them as they are
            decrypt += newmessage[i];
        }
    }
    return decrypt;
}

int main()
{
    string m;
    int k;
    cout << "Enter the Key:\n";
    cin >> k;
    cout << "Enter a message:\n";
    cin.ignore(); // Consume the newline character left in the input buffer
    getline(cin, m); // Read the entire line as input

    string encryptedMessage = encrypt(m, k);
    string decryptedMessage = decrypt(encryptedMessage, k);

    cout << "Encrypted Message is:\n" << encryptedMessage << "\n";
    cout << "Decrypted Message is:\n" << decryptedMessage;

    return 0;
}
