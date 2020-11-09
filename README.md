1. Создаем контейнеры Docker командой: docker-compose up
2. Запускаем симулятор банковских сервисов:
     - переходим в папку симулятора командой: cd gate-simulator
     - запускаем симулятор командой: npm start
3. Запускаем приложение командой: java -jar artifacts/aqa-shop.jar
4. Запускаем тесты:
    - gradlew test --tests PayByCardTest
