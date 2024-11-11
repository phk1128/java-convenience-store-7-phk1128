# java-convenience-store-precourse

## 기능 구현 목록 📝

### ✅ 비즈니스 로직 분석
#### 1. 프로모션
##### - 프로모션 재고 부터 차감 한다.
##### - 프로모션 재고가 부족할 경우 일반 재고에서 차감 한다.
##### - 프로모션을 안내하지 않는 경우
- 프로모션 기간이 아닐때
- 프로모션 조건을 만족할 때
##### - 프로모션을 안내하는 경우
- 프로모션을 받을 수 있을때
- 프로모션 조건을 만족하지 않을때
##### - 프로모션 안내는 각 상품별로 해야한다.
- 안내 순서
- 프로모션 받을 수 있는 경우 -> 프로모션을 받을 수 없는 경우
##### - 프로모션을 받을 수 없는 경우는 총 3가지 이다.
- 프로모션 중이나 재고가 부족한 경우
- 구매 수량의 갯수가 buy + get의 배수가 아닌 경우
  - 2 + 1 프로모션 콜라 7개 일반 콜라 10개가 있을때
  - 10개를 구매하면 프로모션 재고의 3의 배수까지는 프로모션을 받을 수 있음
- 프로모션을 받을 수 있음에도 사용자가 프로모션을 거절한 경우
##### - 프로모션 예시
- `[컵라면-1],[콜라-12],[오렌지주스-1],[감자칩-2]`
- 컵라면은 프로모션 재고가 부족하여 프로모션을 받을 수 없다
- 콜라는 3의 배수인 9개를 제외한 나머지 3개는 프로모션을 받을 수 없다
- 오렌지주스는 프로모션 재고가 있으므로 프로모션을 받을 수 있다
- 감자칩은 이미 프로모션이 적용되어 있다
##### - 프로모션이 안되는 상품을 구매하지 않을 경우 해당 상품 갯수만큼 구매 목록에서 차감한다.

#### 2. 멤버십 할인
##### - 최대 한도는 8,000원 이다.
##### - 프로모션이 적용된 상품을 제외한 금액에서 30% 디스카운트 한다.
##### - 멤버십 할인 예시
- `[콜라-3],[에너지바-5],[오렌지주스-1]`
- 콜라 3개는 프로모션이 적용 됐으므로 멤버십 할인에서 제외된다.
- 에너지바 5개는 프로모션이 없는 일반 상품이므로 멤버십 할인에 포함된다.
- 오렌지주스를 1개만 구매할 경우에는 프로모션이 적용되지 않는 것이므로 멤버십 할인에 포함된다.
- 만약 오렌지주스 1개를 더 받을 경우 프로모션이 적용되어 멤버십 할인에서 제외된다.

### ✅ 진행 과정

#### 1. 보유하고 있는 상품의 정보를 출력 한다.
```text
안녕하세요. W편의점입니다.
현재 보유하고 있는 상품입니다.

- 콜라 1,000원 10개 탄산2+1
- 콜라 1,000원 10개
- 사이다 1,000원 8개 탄산2+1
- 사이다 1,000원 7개
- 오렌지주스 1,800원 9개 MD추천상품
- 오렌지주스 1,800원 재고 없음
- 탄산수 1,200원 5개 탄산2+1
- 탄산수 1,200원 재고 없음
- 물 500원 10개
- 비타민워터 1,500원 6개
- 감자칩 1,500원 5개 반짝할인
- 감자칩 1,500원 5개
- 초코바 1,200원 5개 MD추천상품
- 초코바 1,200원 5개
- 에너지바 2,000원 5개
- 정식도시락 6,400원 8개
- 컵라면 1,700원 1개 MD추천상품
- 컵라면 1,700원 10개

```

#### 2. 구매 상품을 입력 받는다.
```
구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])
[콜라-3],[에너지바-5]
```
- 존재하지 않는 상품은 구매할 수 없다
- 재고 수량을 초과할 수 없다
- 구매 상품은 중복될 수 없다

#### 3. 프로모션 적용이 가능한 상품에 대해 고객이 해당 수량보다 적게 가져온 경우, 그 수량만큼 추가 여부를 입력 받는다.
```text
현재 {상품명}은(는) 1개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)
Y
```
- Y: 증정 받을 수 있는 상품을 추가한다.
- N: 증정 받을 수 있는 상품을 추가하지 않는다.

#### 4. 프로모션 재고가 부족하여 일부 수량을 프로모션 혜택 없이 결제해야 하는 경우, 일부 수량에 대해 정가로 결제할지 여부를 입력받는다.
```text
현재 {상품명} {수량}개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)
Y
```
- Y: 일부 수량에 대해 정가로 결제한다.
- N: 정가로 결제해야하는 수량만큼 제외한 후 결제를 진행한다.

#### 5. 멤버십 할인 적용 여부를 입력 받는다.
```text
멤버십 할인을 받으시겠습니까? (Y/N)
Y
```
- Y: 멤버십 할인을 적용한다.
- N: 멤버십 할인을 적용하지 않는다.

#### 6. 구매 상품 내역, 증정 상품 내역, 금액 정보를 출력한다.
```text
==============W 편의점================
상품명		수량	금액
콜라		3 	3,000
에너지바 		5 	10,000
=============증	정===============
콜라		1
====================================
총구매액		8	13,000
행사할인			-1,000
멤버십할인			-3,000
내실돈			 9,000
```

#### 7. 추가 구매 여부를 입력 받는다.
```text
감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)
Y
```
- Y: 재고가 업데이트된 상품 목록을 확인 후 추가로 구매를 진행한다.
- N: 구매를 종료한다.

### ✅ 실행 예시
```
안녕하세요. W편의점입니다.
현재 보유하고 있는 상품입니다.

- 콜라 1,000원 10개 탄산2+1
- 콜라 1,000원 10개 
- 사이다 1,000원 8개 탄산2+1
- 사이다 1,000원 7개 
- 오렌지주스 1,800원 9개 MD추천상품
- 오렌지주스 1,800원 재고 없음 
- 탄산수 1,200원 5개 탄산2+1
- 탄산수 1,200원 재고 없음 
- 물 500원 10개 
- 비타민워터 1,500원 6개 
- 감자칩 1,500원 5개 반짝할인
- 감자칩 1,500원 5개 
- 초코바 1,200원 5개 MD추천상품
- 초코바 1,200원 5개 
- 에너지바 2,000원 5개 
- 정식도시락 6,400원 8개 
- 컵라면 1,700원 1개 MD추천상품
- 컵라면 1,700원 10개 

구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])
[콜라-3],[오렌지주스-1],[컵라면-1]

현재 오렌지주스은(는) 1개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)
N

현재 오렌지주스 1개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)
Y

현재 컵라면 1개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)
N

멤버십 할인을 받으시겠습니까? (Y/N)
Y

==============W 편의점================
상품명			수량	  금액
콜라              	3         3,000 
오렌지주스           	1         1,800 
=============증	    정===============
콜라             	1        
====================================
총구매액            	4         4,800 
행사할인             		  -1,000 
멤버십할인            		  -540   
내실돈              		  3,260 

감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)
Y

안녕하세요. W편의점입니다.
현재 보유하고 있는 상품입니다.

- 콜라 1,000원 7개 탄산2+1
- 콜라 1,000원 10개 
- 사이다 1,000원 8개 탄산2+1
- 사이다 1,000원 7개 
- 오렌지주스 1,800원 8개 MD추천상품
- 오렌지주스 1,800원 재고 없음 
- 탄산수 1,200원 5개 탄산2+1
- 탄산수 1,200원 재고 없음 
- 물 500원 10개 
- 비타민워터 1,500원 6개 
- 감자칩 1,500원 5개 반짝할인
- 감자칩 1,500원 5개 
- 초코바 1,200원 5개 MD추천상품
- 초코바 1,200원 5개 
- 에너지바 2,000원 5개 
- 정식도시락 6,400원 8개 
- 컵라면 1,700원 1개 MD추천상품
- 컵라면 1,700원 10개 

구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])
[감자칩-8],[컵라면-2],[물-3],[탄산수-2]

현재 감자칩 4개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)
Y

현재 컵라면 2개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)
Y

현재 탄산수은(는) 1개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)
Y

멤버십 할인을 받으시겠습니까? (Y/N)
Y

==============W 편의점================
상품명			수량	  금액
감자칩             	8         12,000
컵라면             	2         3,400 
물               	3         1,500 
탄산수             	3         3,600 
=============증	    정===============
감자칩            	2        
탄산수            	1        
====================================
총구매액            	16        20,500
행사할인             		  -4,200 
멤버십할인            		  -3,270 
내실돈              		  13,030

감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)
Y

안녕하세요. W편의점입니다.
현재 보유하고 있는 상품입니다.

- 콜라 1,000원 7개 탄산2+1
- 콜라 1,000원 10개 
- 사이다 1,000원 8개 탄산2+1
- 사이다 1,000원 7개 
- 오렌지주스 1,800원 8개 MD추천상품
- 오렌지주스 1,800원 재고 없음 
- 탄산수 1,200원 2개 탄산2+1
- 탄산수 1,200원 재고 없음 
- 물 500원 7개 
- 비타민워터 1,500원 6개 
- 감자칩 1,500원 재고 없음 반짝할인
- 감자칩 1,500원 2개 
- 초코바 1,200원 5개 MD추천상품
- 초코바 1,200원 5개 
- 에너지바 2,000원 5개 
- 정식도시락 6,400원 8개 
- 컵라면 1,700원 재고 없음 MD추천상품
- 컵라면 1,700원 9개 

구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])
[콜라-10]

현재 콜라 4개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)
Y

멤버십 할인을 받으시겠습니까? (Y/N)
Y

==============W 편의점================
상품명			수량	  금액
콜라              	10        10,000
=============증	    정===============
콜라             	2        
====================================
총구매액            	10        10,000
행사할인             		  -2,000 
멤버십할인            		  -1,200 
내실돈              		   6,800 

감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)

```

### ✅ 클래스 및 기능

#### 1. product

##### Product
- [X] 이니셜 상품 데이터와 매핑되는 객체
- [X] 상품의 모든 정보를 관리

##### ProductRepository
- [X] 상품이 저장되어 있는 보관소
- [X] 상품의 이름으로 상품을 가져옴
- [X] 상품의 총 재고 카운팅
- [X] 상품의 이름과 프로모션의 유무로 상품을 가져옴
- [X] 상품의 이름으로 상품의 재고가 있는지 확인
- [X] 상품의 이름으로 상품이 존재하는지 확인
- [X] 모든 상품을 가져옴

###### 예외 처리
- [X] 상품 데이터를 가져올 수 없는 경우

##### PurchaseProduct
- [X] 구매 상품을 저장
- [X] 구매 상품이 프로모션 중인지 확인
- [X] 구매 상품의 증정 개수를 반환
- [X] 구매 상품이 프로모션을 받을 수 있는지 확인
- [X] 구매 상품 중 프로모션 상품의 가격을 계산
- [X] 구매 상품 중 비프로모션 상품의 가격을 계산
- [X] 구매 상품 중 일반상품의 가격을 계산
- [X] 구매 상품의 총 가격을 계산

##### ProductService
- [X] 구매 상품들을 프로모션, 비프로모션, 일반 상품으로 구분하여 생성
- [X] 현재 재고관리 되는 상품 내역을 반환 

###### 예외 처리
- [X] 구매 요청 상품의 재고가 부족한 경우
- [X] 구매 요청 상품이 존재하지 않는 경우
- [X] 구매 요청 상품에 중복이 존재하는 경우
- [X] 구매 요청 상품의 수량이 0개 이하인 경우

##### ProductController
- [X] 현재 상품 재고 현황을 응답
- [X] 사용자에게 구매 상품을 요청

#### 2. promotion

##### Promotion
- [X] 프로모션 기간 인지 확인
- [X] 프로모션 할인을 받을 수 있는지 확인
- [X] 이니셜 프로모션 데이터와 매핑되는 객체
- [X] 프로모션의 모든 정보를 관리

##### PromotionRepository
- [X] 프로모션이 저장되어 있는 보관소
- [X] 프로모션의 이름으로 프로모션을 가져옴
- [X] 모든 프로모션을 가져옴

##### PromotionService
- [X] 프로모션 할인을 받을 수 있는 구매 상품이 있는지 확인
- [X] 프로모션 할인을 받을 수 없는 구매 상품이 있는지 확인
- [X] 프로모션 할인을 받을 경우 해당 되는 상품 증정
- [X] 프로모션 할인을 받지 않을 경우 비프로모션 상품 비구매 처리
- [X] 구매 상품 중 프로모션 할인 상품 필터링 하여 반환
- [X] 구매 상품 중 프로모션 비할인 상품 필터링 하여 반환

##### PromotionController
- [X] 사용자에게 프로모션 할인 희망 여부 요청

#### 3.payment

##### Payment
- [X] 멤버십 할인을 받을 수 있는지 확인
- [X] 멤버십 할인 여부에 따라 할인 가격 계산
- [X] 총 구매액을 계산
- [X] 총 구매수량을 계산
- [X] 행사할인 가격을 계산
- [X] 최종 결제 금액(내실돈) 계산


##### PaymentService
- [X] 최종 구매 상품 내역을 반환
- [X] 증정 상품 내역을 반환
- [X] 상품 재고 차감

##### PaymentController
- [X] 멤버십 할인 희망 여부 요청
- [X] 최종 구매 상품 내역 응답
- [X] 증정 상품 내역 응답
- [X] 결제 내역 응답
- [X] 추가 구매 희망 여부 요청

#### 4. view

##### ConsoleInputView
- [X] 사용자가 입력한 구매 상품을 읽음
- [X] 사용자가 입력한 커멘드를 읽음

##### ConsoleProductOutputView
- [X] 인트로 출력
- [X] 현재 상품 상태 출력
- [X] 구매 상품 요청 메세지 출력

##### ConsolePromotionOutputView
- [X] 프로모션 비할인 여부 안내 출력
- [X] 프로모션 할인 여부 안내 출력

##### ConsolePaymentOutputView
- [X] 멤버십 할인 여부 안내 출력
- [X] 총 구매 상품 내역 출력
- [X] 증정 상품 내역 출력
- [X] 총 구매액 출력
- [X] 행사할인 출력
- [X] 멤버십 할인 출력
- [X] 결제액(내실돈) 출력

#### 5. state

##### PurchaseState
- [X] 구매 상품들의 상태를 관리

#### 6. config

##### InitialDataLoader
- [X] 읽은 파일을 각 레파지토리에 저장

##### AppConfig
- [X] 객체 생성 및 의존성 설정

#### 7. util

##### ListValidator
- [X] 리스트 중복 여부 검증

##### StringValidator
- [X] 입력 형식 검증

##### StringParser
- [X] 문자열의 특정 패턴 제거
- [X] 문자열을 리스트(문자열)로 변환
- [X] 문자열을 숫자로 변환

#### 8. error

##### AppException
- [X] `IllegalArgumentException`을 상속 받음
- [X] 하위 클래스
    - [X] NotFoundProductException
    - [X] InvalidWrongInputException
    - [X] InvalidFormatProductAndQuantityException
    - [X] InvalidFailInitialDataLoadException
    - [X] InvalidFailDataReadFromRepositoryException
    - [X] ExceedsProductQuantityException

##### ErrorMessage
- [X] 에러 메세지를 정의

#### 9. constant

##### StoreCommand
- [X] 스토어 커멘드를 정의 (YES or NO)

### ✅ 클래스 다이어그램

#### 1. Convenience
![4주차_다이어그램_메인컨트롤러](https://github.com/user-attachments/assets/b9737c39-cca9-4ec0-a06f-4ca2cd431ae1)

#### 2. State
![4주차_다이어그램_state](https://github.com/user-attachments/assets/ac2e4b60-0897-4a7d-acbf-77b236634fa0)
#### 3. Product
##### Main
![4주차_다이어그램_product_메인](https://github.com/user-attachments/assets/58b176cf-baf2-422b-af57-868e60a630d7)

##### View
![4주차_다이어그램_product_뷰](https://github.com/user-attachments/assets/ac817266-8c3d-41d0-b79e-64a0eb873ae3)

#### 4. Promotion
##### Main
![4주차_다이어그램_promotion_메인](https://github.com/user-attachments/assets/b915505b-abc4-44fb-b1eb-e461c467a32c)

##### View
![4주차_다이어그램_promotion_뷰](https://github.com/user-attachments/assets/ea82846d-2105-4d34-8e2d-06435f843d74)

#### 5. Payment
##### Main
![4주차_다이어그램_payment_메인](https://github.com/user-attachments/assets/622b276c-2641-4c10-91c1-f10409d14d84)

##### View
![4주차_다이어그램_payment_뷰](https://github.com/user-attachments/assets/f0dc0c0c-9944-48e7-a31f-665c866d3e1d)

#### 6. InitialDataLoader
![4주차_다이어그램_이니셜로더](https://github.com/user-attachments/assets/0b73d193-f6e9-46ce-b3bd-3c1c343a2f78)

#### 7. AppConfig
![4주차_다이어그램_config](https://github.com/user-attachments/assets/ef2e289a-94e0-4b72-99ce-82eeb0977865)

#### 8. Util
![4주차_다이어그램_유틸](https://github.com/user-attachments/assets/3ca1507c-7c61-4ca0-ba2c-5276efa32a8e)
