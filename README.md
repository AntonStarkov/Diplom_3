# Diplom_3 
## Параметры для запуска тестов
### Yandex browser
mvn clean test -Ddriver.version=127.0.6533.99 -Dwebdriver.yandex.bin=<путь до бинарника ЯндексБраузера> -Dbrowser=YANDEX
### Chrome browser
mvn clean test -Dbrowser=CHROME

**Для запуска тестов использовались:**
- ChromeDriver 129.0.6668.91
- Google Chrome 129.0.6668.101
- Yandex Browser 24.10.1.598 (64-bit) / 128.0.6613.598 (64-разрядная версия)