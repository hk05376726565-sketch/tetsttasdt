# Kanal 38 – Android Uygulaması

Açılışta sadece Kanal 38 logosu gösterilir. Logoya dokunulunca canlı yayın (HLS / m3u8) açılır.
Uygulama arka planda çalışmaz: geri tuşu yayını durdurup logoya döner, uygulamadan çıkınca yayın tamamen kapanır.

## APK'yı GitHub ile derleme

1. GitHub'da yeni bir repo oluştur.
2. Bu klasörün içindekileri repoya yükle (`.github` klasörü dahil).
3. Repo sayfasında **Actions → Build APK** sekmesine git (push sonrası otomatik çalışır; çalışmazsa **Run workflow**).
4. İş bitince çalışmanın sayfasındaki **Artifacts** bölümünden `Kanal38-apk` dosyasını indir, zip'ten çıkan `app-debug.apk` dosyasını telefona kur.

## Yayın adresini değiştirme

`app/src/main/java/com/kanal38/app/MainActivity.kt` içindeki `STREAM_URL` değerini düzenle.
