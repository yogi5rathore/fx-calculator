Tech Stack
1. Java 1.8
2. Spring boot 2.1.4.RELEASE
3. Spring boot web
4. Junit
5. Apache poi

Tools
1. Eclipse
2. Open Office
3. Postman


Multiple ways to run
1. Sprint boot runner class - FxApplication
2. Spring boot test class - FxApplicationTests
3. http://localhost:8080/currency/convert/<AUD 100.00 in USD>

Details on Fx Calculator
1. Currency Matrix stored in ./config/Currency_Matrix.xls
2. Currency Rate Pairs stored in ./config/currency-pairs-rate.properties
3. Currency Precison stored in ./config/currency-precision.properties

Controller
FxController("/currency")

@Service
FxCalculatorService

Enum
FeedEnum

Validator
FxValidation

Custom Exception
FxException

