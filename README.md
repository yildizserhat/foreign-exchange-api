
# Foreign Exchange

Developed Foreign Exchange APIs. Currencies are taken from https://ratesapi.io/.
## API Reference

#### GET Exchange Rate API

```http
  GET /api/exchange
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `source` | `ENUM` | Currency |

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `target` | `ENUM` | Currency |

#### GET Conversion API that returns the amount in target currency.

{
    "sourceAmount": 100.00,
    "sourceCurrency": "USD",
    "targetCurrency": "TRY"
}

#### GET Conversion List API

```http
  GET /api/conversions/search/findByTransactionDate
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `date`      | `Date` | the format should be 'yyyy-MM-dd'|


```http
  GET /api/conversions/search/findByTransactionId
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `String` | the transaction Id|

#### GET All Conversion records.

```http
  GET /api/conversions/
```







  
