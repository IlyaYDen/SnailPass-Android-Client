<p align="center">
  <img src="https://user-images.githubusercontent.com/90569114/224314316-5cf6cc1f-69e3-4a03-a4e6-ec534c505534.svg" width=25% height=25%>
</p>
# SnailPass
SnailPass is a simple client-server password manager, that i'm writing with my colleagues to make for ourselves a convenient, custom environment for storing passwords.
Now is proposed to make applications for three platforms. The client for each of the platforms is located in different repositories:
- [Windows](https://github.com/badlocale/SnailPass-Desktop-Client)
- Android (you are here now)
- IOS (repository is still hiden)
<!-- -->
The application also has a [server part](https://github.com/rebmanop/SnailPass-REST-API), where user data will be stored, validated and processed.

# How it works
The main idea of the application is storing user's passwords and other information in encrypted form. Encryption is carried out using a symmetric algorithm with a hashed master password as a key. At the same time a key is not stored anywhere and is never transmitted via the network, the user just has to remember it. For this reason, the data can be decrypted only locally and the server stores only the cipher.
#### Cryptographic algorithms
- AES-CBC as a symmetric-key algorithm.
- Pbkdf2 as a key derivation function.
- SHA-512 as a hash function.
#### Libraries and frameworks
Application written using Kotlin, Android API and MVVM and several external libraries:
- Dagger2 as DI container.
- GSON for simplifying JSON boilerplate.
- Retrofit for network.
- Room for manage data.
<!-- -->
# Screenshots
testtesttest
Registration

![image](https://user-images.githubusercontent.com/90569114/192387721-c53e50b8-1c83-43aa-a37e-35c331767812.png)

Sign in

![image](https://user-images.githubusercontent.com/90569114/192387795-95bda620-a0fa-4bf7-93fd-e1093ccdac68.png)

Accounts list

![image](https://user-images.githubusercontent.com/90569114/192387867-bdea0a9c-5d48-454a-8a20-1cf4c65d4890.png)
