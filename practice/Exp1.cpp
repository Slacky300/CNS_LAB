#include <bits/stdc++.h>
using namespace std;

string encrypt(string msg, int key){
    string encrypted_msg = "";
    for(int i = 0; i<msg.length();i++){
        if(isupper(msg[i])){
            encrypted_msg += char(int(msg[i] + key -65)% 26 + 65);
        }else if(islower(msg[i])){
            encrypted_msg += char(int(msg[i] + key - 97)% 26 + 97);
        }else{
            encrypted_msg += msg[i];
        }
    }
    return encrypted_msg;
}

string decrypt(string msg, int key){
    string decrypted_msg = "";
    for(int i = 0; i<msg.length();i++){
        if(isupper(msg[i])){
            decrypted_msg += char(int(msg[i] - key - 65 + 26)% 26 + 65);
        }else if(islower(msg[i])){
            decrypted_msg += char(int(msg[i] - key - 97 + 26)% 26 + 97);
        }else{
            decrypted_msg += msg[i];
        }
    }
    return decrypted_msg;
}


int main(){
    string msg;
    int key;
    cout<<"Enter the key: ";
    cin>>key;
    int c;
    cout<<"------------------Encryption--------------"<<endl;
    while(true){
        cout<<"1.Encrypt\n2.Decrypt\n3.Change Key\n4.Exit\n Enter your choice: ";
        cin>>c;
        if(c == 1){
            cout<<"Enter the message to encrypt: ";
            cin>>msg;
            cout<<"Encrypted Message: "<<encrypt(msg,key)<<endl;
        }else if(c==2){
            cout<<"Enter the message to decrypt: ";
            cin>>msg;
            cout<<"Decrypted message: "<<decrypt(msg,key)<<endl;
        }else if(c==3){
            cout<<"Enter the key: ";
            cin>>key;
        }else if(c==4){
            break;
        }else{
            cout<<"Invalid Choice"<<endl;
        }
    }
    cout<<"------------------Encryption Ends--------------";

}