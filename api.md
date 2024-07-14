## Картошка

запрос/ответ в форме application/json 

users, sessions, wallets, payments, money_transfers 

POST /users - регистрация нового пользователя

Body:
```
{
    "firstName": "string"
    "secondName": "string"
    "phone": "string"
    "email": "string"
    "birthdate": "string"
    "password": "string"
}
```
1. firstName - имя пользователя

2. secondName -  фамилия пользователя. Допускается только буквы русского алфавита. Первые буквы - заглавные. Не более 50 символов по отдельности

3. phone - номер мобильного телефона. Формат телефона: 11 цифр, начинается с '7' К номеру телефона может быть привязан только один пользователь

4. email - почта пользователя

5. birthdate - дата рождения в ISO 8601

6. password - пароль
- от 8 до 64 символов
- Только латинские символы, цифры, знаки только !?
- Обязательно наличие минимум 1 буквы верхнего и нижнего регистра, цифры и знака

Response: 

Http Status Code: 200:
```
{
    "id": "number"
}
```

Http status code: 400:
```
{
    "message": "incorrect_request_format_message"
}
```

Http status code: 409:
```
{
        "message": "user_already_exist"
}
```

GET /users/{id} - получить информация о пользователе с номером {id}

Response

Http Status Code: 200
```
{
    "firstName": "string"
    "secondName": "string"
    "phone": "number"
    "email": "string"
    "birthdate": "string"
}
```

Http Status Code: 404
```
{}
```

PATCH /users/{id} - изменить информацию о пользователе с номером {id}

Body:
```
{
    "firstName": "string"
    "secondName": "string"
    "birthdate": "string"
}
```

Response: 

Http Status Code: 200
```
{}
```

Http status code: 400:
```
{
    "message": "incorrect_request_format_message"
}
```

Http Status Code: 404
```
{}
```

POST /sessions - создание новой сессии пользователем

Body
```
{
    "userEmail": "string"
    "userPassword": "string"
}
```

Response

Http Status Code: 200:
```
{
    "sessionId": "number"
    "sessionDieTime": "string" 
}
```
1. sessionId - номер сессии для пользователя отправившего запрос

2. sessionDieTime - время завершения работы сессии в ISO 8601

Http Status Code 400:
```
{
    "message": "password_incorrect_message"
}
```

GET /sessions/{sessionId} - получение информации о сессии {sessionId}

Response:

Http status code: 200:
```
{
    "userId": "number"
    "sessionDieTime": "string" 
}
```

Http status code: 404:
```
{}
```

DELETE /sessions/{sessionId} - выход из аккаунта с сессией {sessionId}

Response:

Http status code: 200:
```
{}
```

Http status code: 404:
```
{}
```

GET /wallets/{userId}

Response:

Http status code: 200:
```
{
    "id": "number"
    "balance": "number"
    "userId": "number"
}
```

Http status code: 400:
```
{}
```

POST /payments - создание счета на оплату

Body
```
{
    "creatorId": "number"
    "payerId": "number"
    "amount: "number"
    "comment": "string"
}
```

1. creatorId - id пользователя создавшего счет на оплату

2. payerId - id пользователя плательщика

3. amount - размер необходимой оплаты (целое положительное число)

4. comment - комментарий, не более 250 символов, строка произвольная

Response

Http status code: 200:
```
{
    "paymentId": "char[128]"
    "status": "string"
    "date": "string"
}
```

1. paymentId - номер созданного счета в UUID формате

2. status - PAID, UNPAID, CANCELED

3. date - дата выставления счёта в ISO 8601 формате


Http status code : 400:
```
{
    "message": "incorrect_request_format_message"
}
```

DELETE /payments/{paymentId}/users/{creatorId} - отмена счета с номером {paymentId} пользователем {creatorId} создавшим его

Body
```
{}
```

Response

Http status code: 200:
```
{}
```

Http status code: 401:
```
{
    "message": "unauthorized_user_message"
}
```

Http status code: 404:
```
{
    "message": "not_found_message"
}
```

GET /payments/bill/users/{userId} - получение выставленных счетов пользователем {userId}
GET /payments/unpaid/users/{userId} - получение счетов, которые необходимо оплатить пользователю {userId}

Response

Http status code: 200:
```
[
{
    "paymentId": "number"
    "amount: "number"
    "creatorId": "number"
    "payerId": "number"
    "comment": "string"
    "date": "localdatetime"
    "status": "string"
}
]
```

Http status code: 404:
```
{}
```

GET /payments/{paymentOperationId}/users/{userId} - получение выставленного счета с номером {paymentOperationId} пользователем {userId}

Response

Http status code: 200:
```
{
    "paymentOperationId": "number"
    "paymentAmount: "number"
    "paymentCreatorId": "number"
    "payerId": "number"
    "comment": "message"
    "createPaymentDate": "string"
    "paymentStatus": "number"
}
```

Http status code: 404:
```
{}
```

POST /payments/pay/{paymentOperationId}/users/{userId} - оплата счета с номером {paymentOperationId}, пользователем {userId}

Body
```
{}
```

Responce

Http status code: 404:
```
{}
```

Http status code: 405:
```
{
    "message": "not_enought_money_on_wallet_message"
}
```

POST /transfer - перевод денежных единиц от одного пользователя к другому по номеру кошелька или по номеру телефона, или денежных средств пользователем за услугу

Body
```
{
    "senderWalletId": "number"
    "destination": "string"
    "userTransferWay": "string"
    "receiverWalletId": "number"
    "receiverPhone": "number"
    "serviceId": "number"
    "amount": "number"
}
```
1. senderWalletId - номер кошелька отправителя

2. destination - тип пункта назвачения. Принимает значения: USER - перевод для пользователя; SERVICE - перевод за услугу

4. userTransferWay - каким способом отправить пользователю. Принимает значения: BY_ID - перевод по номеру кошелька; BY_TELEPHONE - перевод по номеру телефона

5. receiverWalletId - номер кошелька получателя (не используется, если используется телефон или оплата за услугу)

6. receiverPhone - номер телефона получателя (не используется, если используется номер кошелька или оплата за услугу)

7. serviceId - номер оплачиваемой услуги (не используется, если перевод пользователю)

8. moneyTransferAmount - сумма перевода

Response

Http status code: 200:
```
{
    "transferId": "number"
    "status": "boolean"
    "date": "string"
}
```

1. transferDate - дата и время оплаты в формате ISO 8601

2.  transferStatus - статус перевода: true - успешно, false - неудачно

Http status code: 404:
```
{}
```

Http status code 405:
```
{
    "message": "not_enought_money_message"
}
```

Http status code: 503:
```
{
    "message": "cannot_create_money_transfer_message"
}
```

GET /transfer/wallet/{walletId} - получение информации и всех переводах кошелька {walletId}

Response

Http status code: 200:
```
[
{
    "id": "number"
    "type": "string"
    "secondSideUserId": "number"
    "secondSideWalletId": "number"
    "transferAmount": "number"
    "transferStatus": "boolean"
    "date": "string:
}
]
``` 
1. id - номер перевода
 
2. type - тип перевода: 0 - отправление, 1 - получение

3. secondSideUserId - номер пользователя с которым была совершена операция

3. secondSideWalletId - номер счета пользователя с которым была совершена операция

4. transferAmount - размер перевода (целое положительное число)

5. transferStatus - статус перевода: true - перевод успешен; false - перевод отменен 

6. date - дата и время совершения операции в ISO 8601

Http status code: 404:
```
{}
```

GET /transfer/{transferId}/wallet/{walletId} - получение информации о переводе с номером {transferId}

Response

Http status code: 200:
```
{
    "id": "number"
    "type": "number"
    "secondSideUserId": "number"
    "secondSideWalletId": "number"
    "amount": "number"
    "transferStatus": "number"
    "date": "string:
}
``` 

Http status code: 404:
```
{}
```

GET /transfer/wallet/{walletId}/parameters?transferType=SEND&transferStatus=TRUE - получение информации о переводах с фильтрами по типу перевода и его статусу

параметры transferType и transferStatus необязательны, принимают значения:
transferType - SEND, RECEIVE
transferStatus - TRUE, FALSE

Response

Http status code: 200:
```
[
{
    "id": "number"
    "type": "number"
    "secondSideId": "number"
    "transferAmount": "number"
    "transferStatus": "number"
    "date": "string:
}
]
```

Http status code: 404:
```
{}
```