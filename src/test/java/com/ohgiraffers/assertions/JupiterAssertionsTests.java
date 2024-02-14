package com.ohgiraffers.assertions;

import com.ohgiraffers.assertions.section01.jupiter.Calculator;
import com.ohgiraffers.assertions.section01.jupiter.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class JupiterAssertionsTests {
    /* 이번 수업목표는
    junit5(주피터) 에서 제공하는 어설션 메소드에 대해 이해.
    해보자!
    어설션은 로직이 잘 돌았는지 검증하는 메소드!
    로, 자바8람다 표현식으로 추가된 메소드도 제공.
     */

    //모든 jupiter Assertions의 메소드는 정적(static) 메소드 형태로 제공하고있다.
//테스트코드작성시, 가장맣이하는 패턴은 3개   'given' 'when' 'then' 으로
    //given: 테스트시 필요한 파라미터를 준비한다.
    //when: 테스트할 메소드 호출
    //then: 그럼 결과가 나오면, 맞는지 확인하는 과정을 갖는..
    // 하나의 테스트 메소드에는 한가지를 검증할 수 있도록 작성하는게 좋다.
    //그리고 종종나오는게, 테스트할 메소드와 검증을 동시에 하는 경우도 잇다.( when & then)

/* 목차1. assertEquals
    필기 assertEquals( ) :실제값과 기대값의 일치여부를 판단한다.
 */
    @Test
    @DisplayName("더하기 기능 테스트 만들어보자")
    void testAssertEqual() {
        

        //given (에선)
        int firstNum=10;
        int secondNum=19;
        int expected=30;


        //when  (에선 메소드호출할테니) 메소드먼저만들러가자 메인-자바- org.example(지우고) ->메소드만들고 다시 테스트로와서

        Calculator calculator= new Calculator();
        int result=calculator.plusTwoNumbers(firstNum, secondNum);
        //그럼 result 와 expected랑 비교하면 되겟지 ㅋ

        //Assertions.assertEquals(expected, result);

        //Assertions.assertEquals(expected, result, 1); //delta:오차허용범위 20이나 19나 패스됨.
        //then  먼저 만들어놓고
            //대부분 테스트메소드는 이 3개 폼으로 작동하니..
        
        //3번째 인자로 문자열로 테스트 실패시 보여줄 메시지를 작성할 수 있다.
        //Assertions.assertEquals(expected,result, "실패햇네");

        //결과는 동일해도 불필요한 경우 메시지를 만들지 않도록 지연로딩을 함.
        Assertions.assertEquals(expected,result,1,() -> "실패시 보여짐");
        
    }

    /* 목차 2 assertNotEquals
        필기: assertNotEquals(expected, actual) 메소드는 실제 값과 기대 값의 불일치 여부를 동일성(같은주소)로 판단

     */
    @Test
    @DisplayName("인스턴스 동일성 비교 테스트")
    void testAssertNotEqualsWithInstance() {
        //given
        Object obj1= new Object();
        //when
        Object obj2= new Object();
        //then
        Assertions.assertNotEquals(obj1,obj2);
    }

    /*목차3 assertNull
    필기:
     */
    @Test
    @DisplayName("null 인지 테스트")
    void testAssertNull(){

        //먼저 3개먼저

        //given
        String str;
        //when
        str="null";  //문자열로 큰따옴표 null이 아닌걸 알수잇음 ㅋ
        //then
        Assertions.assertNull(str);
        //미리3개 나눠두고 시작!
    }

    /* 4번째 진행중..
    assertNotNull(actual) 메소드는 레퍼런스 변수가 null값을 가지지 않는지를 판단.
     */
    @Test
    @DisplayName("null이 아닌지 테스트")
    void testAssertNotNull(){

        //given
        String str;
        //when
        str="nullz";//스트링값으로 널이 들어갔으니 아닌걸 확인할수있다 ㅋ
        //then
        Assertions.assertNotNull(str);
    }

    /* 목차 5: assertTrue
        필기: assertTrue(actual) 메소드는 결과값이 true인지 확인하는 메소드로서,
     */

    @Test
    @DisplayName("두 값이 같은지 확인")
    void testAssertTrue(){

        //given
        int num1=10;
        int num2=10;

        //when
        boolean result=num1==num2;

        //then
        /* assertEquals로 true값과 일치하는지를 확인하는 기능과 동일하다

         */
        //Assertions.assertTrue(result);

        //위와 결과는 동일하지만 표현만다른 코드
        Assertions.assertEquals(true,result);

    }
    /* 목차 6 true했으니 false로
        필기: assertFalse(actual) 메소드는 결과값이  false인지 확인해보자.
     */
    @Test
    @DisplayName("flas인지 확인 = 두 값이 다른지")
    void testAssertFalse(){

        //given
        int num1 = 10;
        int num2=110;

        //when
        boolean result=num1 == num2;
        //then
        Assertions.assertFalse(result);

        //달라야 이 메소드는 패스하게됨 ㅋ
    }

    /* 목차: 7 assertAll
        필기: asseretAll(Executable..) 모든 Assertion이 실행되고 실패가 함께 보고되는그룹화된

        이전 junit5 이전엔 asstion은 1번뿐이 진행못햇는데 ㅋ 이후부턴 이런 차이가생기는군...
        그냥 확인용도를위해 2번째도 허용디ㅚㄴ것
     */
    @Test
    @DisplayName("동시에 여러가지 값에 대한 검증 수행 테스트")
    void testAssertAll() {
        //given
        String firstName = "홍길동";
        String lastName = "홍";

        //when  //펄슨 불러와야하기에 짜보자 객체
        Person person = new Person(firstName, lastName);

        //then
        Assertions.assertAll("그룹화된 테스트 실패시 꺼짐 ",
                () -> Assertions.assertEquals(firstName, person.getFirstName(), () -> "firstName이 잘못됨"),
                () -> Assertions.assertEquals(lastName, person.getLastName(), () -> "lastName이 잘못됨")
        );
    }
}


