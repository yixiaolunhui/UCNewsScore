package com.dalong.ucnewsscore;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dalong.ucnewsscore.view.ScoreRecyclerView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;
/**
**
/
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ScoreRecyclerView mScoreRecyclerView;

    private List<Score> mScores=new ArrayList<>();

    private TextView mScoreTV;

    private Button mScoreBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initData() {
        for (int i=0;i<21;i++){
            Score score=new Score();
            score.setName(String.valueOf(i*10-100));
            mScores.add(score);
        }
    }

    private void initView() {
        mScoreRecyclerView= (ScoreRecyclerView)findViewById(R.id.scoreList);
        mScoreTV= (TextView)findViewById(R.id.score_tv);
        mScoreBtn= (Button)findViewById(R.id.score_btn);
        mScoreBtn.setOnClickListener(this);
        mScoreRecyclerView.setCanAlpha(true);
        mScoreRecyclerView.setAdapter(new CommonAdapter<Score>(this, R.layout.item_list, mScores) {
            @Override
            public void convert(ViewHolder holder, Score s,int position) {
                holder.setText(R.id.scoreNum, s.getName());
            }
        });
        mScoreRecyclerView.setSelectPosition(10);
        mScoreRecyclerView.setOnViewSelectedListener(new ScoreRecyclerView.OnViewSelectedListener() {
            @Override
            public void onSelected(View view, final int position) {
                mScoreTV.setVisibility(View.VISIBLE);
                Log.v("MainActivity", ""+mScores.get(position).getName());
                //初始化
                Animation scaleAnimation = new ScaleAnimation(0f, 1.0f,0f,1.0f,
                        Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,
                        0.5f);
                scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        mScoreTV.setText(mScores.get(position).getName());
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                mScoreTV.setVisibility(View.INVISIBLE);
//                            }
//                        },500);

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                //设置动画时间
                scaleAnimation.setDuration(800);
                mScoreTV.startAnimation(scaleAnimation);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.score_btn:
                int scrore=Integer.valueOf(mScores.get(mScoreRecyclerView.getCurrentPosition()).getName());
                String toast;
                if(scrore>0)toast="亲!谢谢夸奖撒.";
                else toast="你妹啊，啥意思吗？";
                Toast.makeText(this,toast, Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
