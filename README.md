# java-convenience-store-precourse

## 기능 구현 목록 📝

### ✅ 클래스 및 기능

#### 1. product

##### Product
- [] 이니셜 상품 데이터와 매핑되는 객체
- [] 상품의 모든 정보를 관리

##### ProductRepository
- [] 상품이 저장되어 있는 보관소
- [] 상품의 이름으로 상품을 가져옴
- [] 상품의 총 재고 카운팅
- [] 상품의 이름과 프로모션의 유무로 상품을 가져옴
- [] 상품의 이름으로 상품의 재고가 있는지 확인
- [] 상품의 이름으로 상품이 존재하는지 확인
- [] 모든 상품을 가져옴

###### 예외 처리
- [] 상품 데이터를 가져올 수 없는 경우

##### PurchaseProduct
- [] 구매 상품을 저장
- [] 구매 상품이 프로모션 중인지 확인
- [] 구매 상품의 증정 개수를 반환
- [] 구매 상품이 프로모션을 받을 수 있는지 확인
- [] 구매 상품 중 프로모션 상품의 가격을 계산
- [] 구매 상품 중 비프로모션 상품의 가격을 계산
- [] 구매 상품 중 일반상품의 가격을 계산
- [] 구매 상품의 총 가격을 계산

##### ProductService
- [] 구매 상품들을 프로모션, 비프로모션, 일반 상품으로 구분하여 생성
- [] 현재 재고관리 되는 상품 내역을 반환 

###### 예외 처리
- [] 구매 요청 상품의 재고가 부족한 경우
- [] 구매 요청 상품이 존재하지 않는 경우
- [] 구매 요청 상품에 중복이 존재하는 경우

##### ProductController
- [] 현재 상품 재고 현황을 응답
- [] 사용자에게 구매 상품을 요청

#### 2. promotion

##### Promotion
- [] 프로모션 기간 인지 확인
- [] 프로모션 할인을 받을 수 있는지 확인
- [] 이니셜 프로모션 데이터와 매핑되는 객체
- [] 프로모션의 모든 정보를 관리

##### PromotionRepository
- [] 프로모션이 저장되어 있는 보관소
- [] 프로모션의 이름으로 프로모션을 가져옴
- [] 모든 프로모션을 가져옴

##### PromotionService
- [] 프로모션 할인을 받을 수 있는 구매 상품이 있는지 확인
- [] 프로모션 할인을 받을 수 없는 구매 상품이 있는지 확인
- [] 프로모션 할인을 받을 경우 해당 되는 상품 증정
- [] 프로모션 할인을 받지 않을 경우 비프로모션 상품 비구매 처리
- [] 구매 상품 중 프로모션 할인 상품 필터링 하여 반환
- [] 구매 상품 중 프로모션 비할인 상품 필터링 하여 반환

##### PromotionController
- [] 사용자에게 프로모션 할인 희망 여부 요청

#### 3.payment

##### Payment
- [] 멤버십 할인을 받을 수 있는지 확인
- [] 멤버십 할인 여부에 따라 할인 가격 계산
- [] 총 구매액을 계산
- [] 총 구매수량을 계산
- [] 행사할인 가격을 계산
- [] 최종 결제 금액(내실돈) 계산


##### PaymentService
- [] 최종 구매 상품 내역을 반환
- [] 증정 상품 내역을 반환
- [] 상품 재고 차감

##### PaymentController
- [] 멤버십 할인 희망 여부 요청
- [] 최종 구매 상품 내역 응답
- [] 증정 상품 내역 응답
- [] 결제 내역 응답
- [] 추가 구매 희망 여부 요청


#### 4. view

##### ConsoleInputView
- [] 사용자가 입력한 구매 상품을 읽음
- [] 사용자가 입력한 커멘드를 읽음

##### ConsoleOutputView
- [] 인트로 출력
- [] 현재 상품 상태 출력
- [] 구매 상품 요청 메세지 출력
- [] 프로모션 비할인 여부 안내 출력
- [] 프로모션 할인 여부 안내 출력
- [] 멤버십 할인 여부 안내 출력
- [] 총 구매 상품 내역 출력
- [] 증정 상품 내역 출력
- [] 총 구매액 출력
- [] 행사할인 출력
- [] 멤버십 할인 출력
- [] 결제액(내실돈) 출력

#### 5. state

##### PurchaseState
- [] 구매 상품들의 상태를 관리

#### 6. config

##### InitialDataLoader
- [] 읽은 파일을 각 레파지토리에 저장

##### AppConfig
- [] 객체 생성 및 의존성 설정

#### 7. util

##### ListValidator
- [] 리스트 중복 여부 검증

##### StringValidator
- [] 입력 형식 검증

##### StringParser
- [] 문자열의 특정 패턴 제거
- [] 문자열을 리스트(문자열)로 변환
- [] 문자열을 숫자로 변환

#### 8. error

##### AppException
- [] `IllegalArgumentException`을 상속 받음
- [] 하위 클래스
    - [] NotFoundProductException
    - [] InvalidWrongInputException
    - [] InvalidFormatProductAndQuantityException
    - [] InvalidFailInitialDataLoadException
    - [] InvalidFailDataReadFromRepositoryException
    - [] ExceedsProductQuantityException

##### ErrorMessage
- [] 에러 메세지를 정의

#### 9. constant

##### StoreCommand
- [] 스토어 커멘드를 정의 (YES or NO)

##### OutputMessage
- [] 출력 메세지를 정의
