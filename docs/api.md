
## 📑 API 명세서 예시

### 1. 환자 기본 정보 저장
- URL : api/patients
- Method : POST
-
**Request**
~~~json
{
    "name": "홍길동",
    "age": 30,
    "gender": "MALE",
    "disease": true
}
~~~
**Response**
```json
{
  "code": "200",
  "message:": "환자 기본 정보가 저장되었습니다.",
  "data":{
    "name": "홍길동",
    "age": 30,
    "gender": "MALE",
    "disease": true
  }
}
```

---

### 2. 환자 이미지 업로드
- URL : api/patients/{patientId}/image
- Method : POST

**Request**
- file : 이미지 파일 (png 또는 jpg)

**Response**
```json
{
  "code": 200,
  "message": "이미지 업로드가 완료되었습니다.",
  "data": "images/4d207a34-028f-4c06-ab6d-eab98ac12e4d.jpeg"
}
```

---

### 3. 환자 기본 정보 조회
- URL : api/patients/{patientId}
- Method : GET

**Response**
```json
{
  "code": "200",
  "message:": "환자 기본 정보가 저장되었습니다.",
  "data": {
    "patientId": 1,
    "name": "test",
    "age": 10,
    "gender": "MALE",
    "disease": true,
    "imagePath": "images/4d207a34-028f-4c06-ab6d-eab98ac12e4d.jpeg",
    "imageName": "4d207a34-028f-4c06-ab6d-eab98ac12e4d.jpeg",
    "imageUploaded": true
  }
}
```

---

### 4. 환자 이미지 조회
- URL : api/patients/{patientId}/images/{fileName}
- Method : GET


**Response**
```json
{
  "code": 200,
  "message": "환자 이미지가 조회되었습니다.",
  "data": "http://localhost:8080/images/4d207a34-028f-4c06-ab6d-eab98ac12e4d.jpeg"
}
```
---

### 5. 환자 데이터 삭제
- URL : api/patients/{patientId}
- Method : DELETE

**Response**
```json
{
  "code": "200",
  "message:": "환자 기본 정보가 저장되었습니다.",
  "data":{
    "patientId": 1,
    "name": "홍길동",
    "age": 30,
    "gender": "MALE",
    "disease": true
  }
}
```