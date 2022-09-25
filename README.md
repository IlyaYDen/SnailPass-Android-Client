# Snail Password Manager
Snail Password Manager - is easy file manager client-server program. You can keep your passwords in it and get your passwords anywhere, you need just log in on your device. So if you dont have internet connection password will save ofline and you can get it too.
Application will be on PC(C#), Android(Kotlin) and IOS(Swift). 
Server we will make on Python.
Data Base LiteSQL(for PC, Android and IOS) and postgresql for Python server.

This is a mobile android application for Snail Password Manager. With it you can keep your password, save it, got it everywhere, in online or offline mode. 

We try to put a lot of emphasis on safety, we use Pbkdf2(sha-512) hash algorithm to hash master password and AES-256-CBC to encrypt user information(passwords). More easy to understand all of it in diograms.
Here you can see registration prosess in diagram
![image](https://user-images.githubusercontent.com/90569114/192116325-c6516aa0-c4ce-41b4-a719-51fd25d65ee6.png)

and Log In diagram

![image](https://user-images.githubusercontent.com/90569114/192116336-02526e3d-6a13-445b-8ea8-5ba136aad78a.png)
so these diagrams show the full operation of the application with the server. In offline mode, client stores the password sashed 1000 times and checks it against the entered one. And keep passwords on local LiteSQL.

We recently started doing a project, and for a long time we understood the theory of cryptography, and now we ready to start codding. Now it just a developments version of our product.
