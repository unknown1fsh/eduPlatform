# EduPlatform

EduPlatform, online eğitim sunmak isteyen bireyler ve kurumlar için geliştirilmiş basit ama kapsamlı bir **Spring Boot tabanlı online eğitim platformudur**. Kullanıcı yönetimi, kurs oluşturma, kayıt ve ödeme işlemleri gibi temel işlevleri destekler.  

## 🎯 Özellikler

- ✅ Kullanıcı Yönetimi (Kayıt, Güncelleme, Silme)
- 🎓 Kurs Oluşturma ve Yayınlama
- 📝 Kurs Kayıt ve Ödeme İşlemleri
- ⚙️ Parametre Yönetimi (Sistem ayarları, sabit veriler vb.)
- 🔐 Güvenlik Katmanı (Spring Security ile kimlik doğrulama)

## 🧰 Kullanılan Teknolojiler

| Teknoloji       | Versiyon   |
|----------------|------------|
| Java           | 17         |
| Spring Boot    | 3.2.3      |
| MySQL          | 8.0.33     |
| MapStruct      | 1.5.5      |
| Spring Security| Dahil      |
| Lombok         | ✓          |
| Maven          | ✓          |

## 🚀 Kurulum ve Çalıştırma

### 1. Depoyu Klonla

```bash
git clone https://github.com/unknown1fsh/edu-platform.git
cd edu-platform
2. Veritabanı Oluştur
MySQL'de bir veritabanı oluştur:

sql
Kopyala
Düzenle
CREATE DATABASE edu_platform CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
3. application.properties Dosyasını Ayarla
src/main/resources/application.properties dosyasına aşağıdakileri ekle:

properties
Kopyala
Düzenle
spring.datasource.url=jdbc:mysql://localhost:3306/edu_platform
spring.datasource.username=root
spring.datasource.password=12345
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
4. Maven Bağımlılıklarını Yükle
bash
Kopyala
Düzenle
./mvnw clean install
5. Uygulamayı Başlat
bash
Kopyala
Düzenle
./mvnw spring-boot:run
🔐 Kimlik Doğrulama
Spring Security kullanılarak güvenlik sağlanmıştır. İlk kurulumda, kayıt ekranı aracılığıyla kullanıcı oluşturabilir veya Admin olarak sisteme giriş yapabilirsiniz.

📦 Proje Yapısı (Katmanlar)
pgsql
Kopyala
Düzenle
edu-platform/
├── controller       → REST API controller sınıfları
├── service          → İş mantığı (Business Logic)
│   └── impl         → Service implementasyonları
├── dto              → Data Transfer Object’ler
├── entity           → JPA Entity sınıfları
├── mapper           → MapStruct dönüşüm sınıfları
├── repository       → Spring Data JPA Repository’ler
├── security         → Spring Security yapılandırması
└── config           → Global yapılandırmalar (CORS, Exception, vb.)
📌 Örnek API Uçları
İşlem	Endpoint	Metod
Kullanıcı Kaydı	/api/users/register	POST
Kurs Oluşturma	/api/courses	POST
Tüm Kursları Listeleme	/api/courses	GET
Kurs Kaydı Yapma	/api/enrollments	POST
Ödeme İşlemi	/api/payments	POST
📄 Lisans
Bu proje açık kaynaklıdır ve MIT Lisansı ile lisanslanmıştır. Detaylı bilgi için LICENSE dosyasına bakabilirsiniz.
