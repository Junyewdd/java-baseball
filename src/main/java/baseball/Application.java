package baseball;
import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.*;
import java.util.ArrayList;
public class Application {
    private static final int NUM_COUNT = 3;
    private static final int Min_NUM = 1;
    private static final int Max_NUM = 9;
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        do{
            playGame();
            System.out.println("게임 새로 시작하려면 1, 종료하려면 2를 입력하세요.");

        }while("1".equals(Console.readLine()));
    }


    private static void playGame(){
        List<Integer> computerNums = generateRandomNumbers();
        List<Integer> userNums = null;

        System.out.println("숫자 야구 게임을 시작합니다.");

        do{
            try{
                System.out.print("숫자를 입력해주세요 : ");
                userNums = convertInputToNumberList(Console.readLine());
            }catch(IllegalArgumentException e){ // ex. 네 자리 숫자 입력받을 때
                System.out.println("잘못된 값입니다. 다시 시도하세요.");
                continue;
            }
            printResult(getStrikeCount(computerNums, userNums), getBallCount(computerNums, userNums));
        }while(!computerNums.equals(userNums));
        System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
    }


    private static List<Integer> generateRandomNumbers(){
        List<Integer> numbers = new ArrayList<>();
        //세 개의 중복되지 않는 숫자 생성 후 리스트에 저장
        while(numbers.size()<NUM_COUNT){
            int randomNumber = Randoms.pickNumberInRange(Min_NUM, Max_NUM);
            if(!numbers.contains(randomNumber)) {
                numbers.add(randomNumber);
            }
        }

        return numbers;
    }


    private static List<Integer> convertInputToNumberList(String input){
        //입력받은 string에 오류 있는지 확인
        if(input.length()!=NUM_COUNT || !input.matches("[1-9]+")){
            throw new IllegalArgumentException();
        }
        
        List<Integer> numberList = new ArrayList<>();

        //integer 리스트에 숫자로 저장
        for(char c: input.toCharArray()){
            numberList.add(c-'0');
        }

        return numberList;
    }


    private static int getStrikeCount(List<Integer> computer, List<Integer> user){
        int strikeCnt = 0;

        for(int i = 0;i<NUM_COUNT;i++){
            if(computer.get(i).equals(user.get(i)))strikeCnt++;
        }

        return strikeCnt;
    }


    private static int getBallCount(List<Integer> computer, List<Integer> user){
        int ballCnt = 0;

        for(int u:user){
            if(computer.contains(u))ballCnt++;
        }

        return ballCnt - getStrikeCount(computer, user);
    }

    
    private static void printResult(int strikes, int balls){
        if(strikes==0 && balls == 0) {
            System.out.println("낫싱");
        }
        else{
            if(balls>0)System.out.print(balls+"볼 ");
            if(strikes > 0)System.out.print(strikes+"스트라이크");
            System.out.println();
        }
    }

}
