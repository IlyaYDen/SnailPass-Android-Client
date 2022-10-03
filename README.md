# Snail Password Manager
Snail Password Manager - is simple client-server application for storing passwords. You can keep your passwords in it and get them anywhere, you need just log in on your device.
Application will be on PC(C#), Android(Kotlin) and IOS(Swift). 
Server we will make on Python.
Data Base LiteSQL(for PC, Android and IOS) and PostgreSQL for Python server.

This is a mobile android application for Snail Password Manager. With it you can keep your passwords, save it, got it everywhere, in online or offline modes.

We try to put a lot of emphasis on safety, we use Pbkdf2(sha-512) hash algorithm to hash master password and AES-256-CBC to encrypt and decrypt user information(passwords). More easy to understand all of it in diograms.
Here you can see registration prosess in diagram
![image](https://user-images.githubusercontent.com/90569114/192116325-c6516aa0-c4ce-41b4-a719-51fd25d65ee6.png)

and Log In diagram

![image](https://user-images.githubusercontent.com/90569114/192116336-02526e3d-6a13-445b-8ea8-5ba136aad78a.png)

In offline mode, client stores the master password sashed 1000 times and checks it against the entered one. And get passwords from local LiteSQL.


# Android Screenshots
![image](https://user-images.githubusercontent.com/90569114/192387721-c53e50b8-1c83-43aa-a37e-35c331767812.png)

![image](https://user-images.githubusercontent.com/90569114/192387795-95bda620-a0fa-4bf7-93fd-e1093ccdac68.png)

![image](https://user-images.githubusercontent.com/90569114/192387867-bdea0a9c-5d48-454a-8a20-1cf4c65d4890.png)
