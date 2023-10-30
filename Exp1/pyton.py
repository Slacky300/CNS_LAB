def encrypt_func(txt, s):
    result = ""

    # Traverse the plain text
    for char in txt:
        # Encrypt uppercase characters in plain text
        if char.isupper():
            result += chr((ord(char) + s - 65) % 26 + 65)
        # Encrypt lowercase characters in plain text
        else:
            result += chr((ord(char) + s - 97) % 26 + 97)
    
    return result

def decrypt_func(ciphertext, s):
    result1 = ""

    # Traverse the ciphertext
    for char in ciphertext:
        # Decrypt uppercase characters in ciphertext
        if char.isupper():
            result1 += chr((ord(char) - s - 65) % 26 + 65)
        # Decrypt lowercase characters in ciphertext
        else:
            result1 += chr((ord(char) - s - 97) % 26 + 97)
    
    return result1

s = int(input("Enter the shift key: "))
txt = input("Enter a message: ")

encrypted_text = encrypt_func(txt, s)
print("Plain text: " + txt)
print("Shift pattern: " + str(s))
print("Cipher: " + encrypted_text)
decrypted_text = decrypt_func(encrypted_text, s)
print("Decrypted: " + decrypted_text)
