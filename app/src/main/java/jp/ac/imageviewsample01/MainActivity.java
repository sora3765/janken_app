package jp.ac.imageviewsample01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //フィールド
    private boolean flg = true;
    private int rand = 0;
    //gu = 0 pa = 1 tyoki = 2
    private int comHand = 0;
    private int myHand = 0;
    private int win = 0;
    private int lo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //imageViewインスタンス化
        ImageView img = findViewById(R.id.imageView);
        ImageView pRock = findViewById(R.id.p_rock);
        ImageView pScisscors = findViewById(R.id.p_scissors);
        ImageView pPaper = findViewById(R.id.p_paper);

        //えらんだとき
        pRock.setOnClickListener(view ->doJanken(view));
        pScisscors.setOnClickListener(view ->doJanken(view));
        pPaper.setOnClickListener(view ->doJanken(view));

//        //クリックで画像変化
//        img.setOnClickListener(view -> {
//            //ここに書きたいがラムダはnewされているので見られない･･･インスタンスをフィールドにORもう一度宣言
//            changeImg();
//        });

    }

//じゃんけん
    private void doJanken(View view){
        //何が押されたか
        ImageView player = (ImageView) view;
        switch (player.getId()){
            case R.id.p_rock:
                findViewById(R.id.p_scissors).setVisibility(view.INVISIBLE);
                findViewById(R.id.p_paper).setVisibility(view.INVISIBLE);
                myHand = 0;
                comhand();
                break;
            case R.id.p_scissors:
                findViewById(R.id.p_rock).setVisibility(view.INVISIBLE);
                findViewById(R.id.p_paper).setVisibility(view.INVISIBLE);
                myHand = 2;
                comhand();
                break;
            case R.id.p_paper:
                findViewById(R.id.p_scissors).setVisibility(view.INVISIBLE);
                findViewById(R.id.p_rock).setVisibility(view.INVISIBLE);
                myHand = 1;
                comhand();
                break;
        }
        result();
        TextView winlo = findViewById(R.id.winlo);
        winlo.setText(win+"勝"+lo+"敗");
        //手を表示　画像を非表示
//        player.setVisibility(view.INVISIBLE);
    }

    //comのて
    private void comhand() {
        ImageView img = findViewById(R.id.imageView);
        rand = (int) (Math.random() * 3);
        switch (rand) {
            case 0:
                img.setImageResource(R.drawable.rock);
                comHand = 0;
                break;
            case 1:
                img.setImageResource(R.drawable.paper);
                comHand = 1;
                break;
            case 2:
                img.setImageResource(R.drawable.scissors);
                comHand = 2;
                break;
            default:
                System.out.println("エラー");
        }
    }

    //判定
    private void result(){
        TextView resultview = findViewById(R.id.kekka);
        if (comHand == myHand){
            resultview.setText("あいこ");
        }else if(myHand == comHand + 1 || myHand + 2 == comHand){
            resultview.setText("あなたの勝ち");
            win++;
        }else {
            resultview.setText("あなたの負け");
            lo++;
        }
    }

    public void onClickWinres(View view){
        TextView winlo = findViewById(R.id.winlo);
        winlo.setText("0勝0敗");
        win = 0;
        lo = 0;
    }

    public void onClickRes(View view){
        ImageView img = findViewById(R.id.imageView);
        img.setImageResource(R.drawable.janken_boys);
        findViewById(R.id.p_scissors).setVisibility(view.VISIBLE);
        findViewById(R.id.p_rock).setVisibility(view.VISIBLE);
        findViewById(R.id.p_paper).setVisibility(view.VISIBLE);
        TextView resultview = findViewById(R.id.kekka);
        resultview.setText("じゃんけんをしよう！");

    }



}