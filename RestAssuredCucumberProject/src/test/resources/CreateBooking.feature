Feature: Kullanıcının otel rezervasyonu yapabilmesi

  Scenario: Kullanıcı bir otel rezervasyonu oluşturabilir ve silebilir
    Given Kullanıcı yeni bir rezervasyon oluşturur
    And Kullanıcı rezervasyon için gereken bilgileri verir
    When Kullanıcı otel rezervasyonu yaratıyor
    Then Rezervasyon başarılı şekilde oluşturulur
    And Kullanıcı oluşturduğu rezervasyonu iptal eder
    Then Rezervasyon başarılı şekilde silinir