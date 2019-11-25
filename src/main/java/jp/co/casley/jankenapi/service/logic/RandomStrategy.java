package jp.co.casley.jankenapi.service.logic;

import java.util.Random;

/**
 * ランダムストラテジークラス
 */
public class RandomStrategy implements Strategy {
    /** ランダム値 */
    private Random random;

    public RandomStrategy() {
        this.random = new Random();
    }

    @Override
    public String calcHand() {
        return Integer.toString(this.random.nextInt(3));
    }

}
