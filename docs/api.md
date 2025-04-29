
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
  "data": "/images/e3d86776-e30f-4bc3-9b8d-8ddc6665345b.jpeg"
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
  "data":{
    "name": "홍길동",
    "age": 30,
    "gender": "MALE",
    "disease": true,
    "imageUrl": "/images/patient.jpg"
  }
}
```

---

### 4. 환자 이미지 조회
- URL : api/patients/{patientId}/images
- Method : GET

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
    "name": "홍길동",
    "age": 30,
    "gender": "MALE",
    "disease": true,
    "imageUrl": "/images/patient.jpg"
  }
}
```