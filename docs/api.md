
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
    "patientId": 1,
    "message": "환자 기본 정보가 저장되었습니다."
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
    "imageUrl": "/images/patient.jpg",
    "message": "이미지 업로드가 완료되었습니다."
}
```

---

### 3. 환자 기본 정보 조회
- URL : api/patients/{patientId}
- Method : GET

**Response**
```json
{
    "name": "홍길동",
    "age": 30,
    "gender": "MALE",
    "disease": true,
    "imageUrl": "/images/patient.jpg"
}
```

---

### 4. 환자 이미지 조회
- URL : api/patients/{patientId}/images/{fileName}
- Method : GET

---

### 5. 환자 데이터 삭제
- URL : api/patients/{patientId}
- Method : DELETE

**Response**
```json
{
  "message": "환자 데이터가 삭제되었습니다."
}
```