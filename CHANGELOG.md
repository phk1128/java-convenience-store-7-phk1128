#### 2024-11-11

##### Chores

*  디렉토리 구조 변경 (1c73bd0b)
* **ProductType:**  ProductType 삭제 (b516226b)

##### Documentation Changes

* **README:**
  *  기능 구현 목록 수정 (cc0559ac)
  *  클래스 다이어그램 추가 (4babc78b)
  *  비즈니스 분석, 진행 과정 추가 (0324b072)
  *  기능 구현 목록 체크 (2c62a4bf)
  *  기능 구현 목록 수정 (eed199ff)
  *  기능 구현 목록 초안 작성 (8579d960)

##### New Features

* **Convenience:**  메인 컨트롤러 레이어 구현 (94a0e0d9)
* **AppConfig:**  애플리케이션 관련 객체 생성 및 의존성 설정 클래스 구현 (8ac3f77a)
* **OutputView, ConsoleOutputView:**  출력 관련 뷰 레이어 구현 (d6072988)
* **PaymentController:**  결제 관련 컨트롤러 레이어 구현 (ad18f553)
* **PaymentService:**  결제 관련 서비스 레이어 구현 (ef5affd6)
* **PaymentReceipt:**  최종 구매 내역 dto 구현 (6fdd2160)
* **Payment:**  결제 관련 도메인 구현 (bfdb617f)
* **PurchaseProductReceipt, BenefitProductReceipt:**  구매 상품 내역, 증정 상품 내역 dto 구현 (bfa701b5)
* **PromotionController:**  프로모션 관련 컨트롤러 레이어 구현 (c44b6982)
* **PromotionService:**  프로모션 관련 서비스 구현 (9cafd5f7)
* **PromotionProduct, NonPromotionProduct:**  프로모션 상품, 비프로모션 상품 dto 구현 (df27374f)
* **ProductController:**  상품 관련 컨트롤러 레이어 구현 (cdd0b46d)
* **InputView, ConsoleInputView:**  입력 뷰 레이어 구현 (724d7ac7)
* **PurchaseState:**  구매 상태 관리를 위한 클래스 구현 (0888b70f)
* **ProductService:**  상품 관련 서비스 레이어 구현 (03484ad8)
* **PurchaseProductGenerator:**  구매 상품 객체 생성 클래스 구현 (9d113ad5)
* **PurchaseProduct:**  구매 상품 관련 도메인 구현 (6b6fea38)
* **ProductQuantity, PurchaseProductInfo:**  구매 상품 수량 도메인 및 구매 상품 정보 VO 구현 (e1eb0864)
* **ProductStatus, PurchaseProductRequest:**  상품 상태 dto, 구매 상품 요청 dto 구현 (ac9e0579)
* **LoopTemplate:**  try-catch 루프 템플릿 구현 (5246dd65)
* **ListValidator:**  리스트 검증 클래스 구현 (0c07776c)
* **StringValidator:**  문자열 검증 클래스 구현 (43105062)
* **StoreCommand, OutputMessage:**  YES or NO 커멘드 정의 및 출력 메세지 정의 (0573673c)
*  에러 관련 클래스 구현 (42e1a6f7)
* **ProductInfo:**  상품 정보 VO 구현 (871717e5)
* **InitialDataLoader:**  데이터 초기화 클래스 구현 (45a24c43)
* **StringParser:**  문자열 파싱 클래스 구현 (2eb4082a)
* **ProductRepository:**  상품 레파지토리 구현 (848fe9a1)
* **Product:**  상품 도메인 구현 (47661ff9)
* **ProductType:**  상품 타입 정의 (1ae61add)
* **PromotionRepository:**  프로모션 레파지토리 구현 (0db28cb5)
* **Promotion, PromotionDateTime:**  프로모션 관련 도메인 구현 (6a2f661d)
* **PromotionType:**  프로모션 타입 정의 (c4f6f88c)

##### Refactors

* **ProductService:**  구매 상품 요청 수량이 0개인 경우 예외 처리 (7cb0f671)
*  매직넘버 상수화 및 포맷팅 (1c7bb61c)
*  ProductRepository, PromotionRepository 싱글톤 패턴 제거 (ceb60493)
*  view 레이어 및 출력 메세지 상수 각 도메인에 맞게 분리 (38e65a5e)
*  getRemainingQuantity() 메서드 이름 calculateRemainingQuantity()로 수정 (aaebfdd0)
*  디렉토리 구조 변경 싱글톤 패턴 적용 (b2100794)
* **Convenience:**  애플리케이션 종료 전 콘솔 닫기 추가 (1949a862)
* **Promotion:**
  *  canPromotion() 메서드 수정 (14f4fcdf)
  *  매직 넘버 상수화 (e70d8a2e)
* **ProductRepository:**  클리어 메소드 추가 (a40bd4ff)
* **StringParser:**
  *  trim 추가 (167bc6b8)
  *  파싱 실패 시 커스텀 예외 반환 하도록 수정 (40580fb4)
  *  문자열 특정 패턴 제거 추가 및 매직 넘버 상수화 (3f06e83b)
* **Application:**  main 메서드 수정 (c4469418)
* **PromotionRepository:**  싱글톤 패턴 적용 (ff58f347)
* **Product:**  id 값 정의, getter 추가 (b59b8267)
* **InitialDataLoader:**  상품 객체 생성시 id 설정, 예외 처리 수정 (dfda7124)

##### Code Style Changes

* **AppConfig:**  포맷팅 (60ee16c2)

##### Tests

* **PaymentTest:**  결제 관련 도메인 테스트 (b2ac8762)
* **PurchaseProductFixture:**
  *  구매 상품 픽스처 수정 (4dec23b2)
  *  구매 상품 픽스처 수정 (1c081883)
  *  구매 상품 픽스터 수정 (01904568)
  *  테스트를 위한 구매 상품 픽스처 구현 (9cceee12)
* **PromotionServiceTest:**  프로모션 관련 서비스 레이어 테스트 (a8018b3d)
* **PromotionTest:**  프로모션 관련 도메인 테스트 (7326dc6f)
* **ConvenienceTest:**  편의점 통합 테스트 (82798dee)
* **ProductServiceTest:**
  *  상품 관련 서비스 레이어 테스트 (d02d2a3d)
  *  상품 관련 서비스 레이어 테스트 (60cab866)
* **PurchaseProductTest:**  구매 상품 관련 도메인 테스트 (50dc1be0)
* **ProductRepositoryTest:**
  *  통합 테스트 실패 메서드 제거 (49f2ae39)
  *  재고 관리 클래스 테스트 (03187e59)
* **ProductTest:**
  *  픽스처 생성에 따른 리팩토링 (1d0c2811)
  *  상품 도메인 테스트 (d9fe6c40)
* **ProductFixture:**  테스트를 위한 상품 픽스처 구현 (62b28764)
* **InitialDataLoaderTest:**
  *  초기 객체 선언 부분 수정 (f3d21c6b)
  *  디렉토리 구조 변경 (ebfe22fa)
  *  데이터 초기화 클래스 테스트 (1071d9aa)

