# PT Sei Sleksi Magang Java Spring Boot

## Postman Public Rest Api
```
https://www.postman.com/descent-module-specialist-85092779/workspace/rest-api-pt-sei-proyek/collection/15693076-bb93eaca-5edf-4990-ab32-a1481088c528?action=share&creator=15693076 
```

## Mysql Raw Create Database proyek_db
```
CREATE DATABASE proyek_db;

USE proyek_db;

CREATE TABLE lokasi (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nama_lokasi VARCHAR(255) NOT NULL,
    negara VARCHAR(100) NOT NULL,
    provinsi VARCHAR(100) NOT NULL,
    kota VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE proyek (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nama_proyek VARCHAR(255) NOT NULL,
    client VARCHAR(255) NOT NULL,
    tgl_mulai DATETIME NOT NULL,
    tgl_selesai DATETIME NOT NULL,
    pimpinan_proyek VARCHAR(255) NOT NULL,
    keterangan TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE proyek_lokasi (
    proyek_id INT,
    lokasi_id INT,
    PRIMARY KEY (proyek_id, lokasi_id),
    FOREIGN KEY (proyek_id) REFERENCES proyek(id) ON DELETE CASCADE,
    FOREIGN KEY (lokasi_id) REFERENCES lokasi(id) ON DELETE CASCADE
);

```

## Project Structure
```
proyek
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           └── proyek
│   │   │               ├── controller
│   │   │               │   ├── LokasiController.java
│   │   │               │   └── ProyekController.java
│   │   │               ├── entity
│   │   │               │   ├── Lokasi.java
│   │   │               │   └── Proyek.java
│   │   │               ├── exceotion 
│   │   │                   └── ResourceNotFoundException.java
│   │   │               ├── repository
│   │   │               │   ├── LokasiRepository.java
│   │   │               │   └── ProyekRepository.java
│   │   │               ├── service
│   │   │               │   ├── LokasiService.java
│   │   │               │   └── ProyekService.java
│   │   │               ├── utils
│   │   │               │   └── ResponseUtil.java
│   │   │               └── ProyekApplication.java
│   │   └── resources
│   │       ├── application.properties
│   │       └── templates
│   └── test
│       └── java
│           └── com
│               └── example
│                   └── proyek
│                       └── ProyekApplicationTests.java
│
├── .gitignore
├── build.gradle
├── gradlew
├── gradlew.bat
├── HELP.md
├── README.md
└── settings.gradle
```