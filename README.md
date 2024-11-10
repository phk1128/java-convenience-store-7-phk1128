# java-convenience-store-precourse

## 기능 구현 목록 📝

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

##### ConsoleOutputView
- [X] 인트로 출력
- [X] 현재 상품 상태 출력
- [X] 구매 상품 요청 메세지 출력
- [X] 프로모션 비할인 여부 안내 출력
- [X] 프로모션 할인 여부 안내 출력
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

##### OutputMessage
- [X] 출력 메세지를 정의
