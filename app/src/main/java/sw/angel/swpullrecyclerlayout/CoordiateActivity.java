package sw.angel.swpullrecyclerlayout;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.angel.adapter.SWRecyclerAdapter;
import com.angel.adapter.SWViewHolder;

import sw.angel.swpullrecyclerlayout.NumAdapter;
import sw.angel.swpullrecyclerlayout.R;
import sw.coord.ImageBehavior;
import sw.interf.LoadListener;
import sw.loadlayer.LoadLayout;
import sw.loadlayer.LoadingProcess;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/22 0022.
 */

public class CoordiateActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadcover);
        LoadLayout loadLayout = ((LoadLayout) findViewById(R.id.item_loader));

        loadLayout.setPrecessChangeListener(new LoadLayout.onPrecessChangeListener() {
            @Override
            public void onLoadProcessChange(View footer, int process) {

            }

            @Override
            public void onRefreshProcessChange(View header, int process) {

            }

            @Override
            public void onLoad(View footer) {
                LoadingProcess process1 = (LoadingProcess) footer;
                process1.pause();
                process1.startAccAnim();
            }

            @Override
            public void onRefresh(View header) {
                LoadingProcess process1 = (LoadingProcess) header;
                process1.pause();
                process1.startAccAnim();
            }
        });

        RecyclerView recyclerView= loadLayout.getRecycleView();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add(i + 1 + "");
        }
        recyclerView.setAdapter(new SWRecyclerAdapter<String>(this,list){
            @Override
            public int getItemLayoutId(int layoutID) {
                return R.layout.item;
            }
            @Override
            public void bindData(SWViewHolder holder, int position, String item) {
                holder.getTextView(R.id.text).setText(item);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        setBehaviorListener((ImageView) findViewById(R.id.top));
//        setBehaviorListener((ImageView) findViewById(R.id.bottom));
    }

    private void setBehaviorListener(ImageView view){
        ImageBehavior behavior = ImageBehavior.from(view);
        behavior.setListener(listener);
    }


    private LoadListener listener = new LoadListener() {
        @Override
        public void onLoading(ImageBehavior behavior,View view) {
            Log.i("onLoading","ok");
            cancelStatue(behavior,view);
        }

        @Override
        public void onRefresh(ImageBehavior behavior,View view) {
            Log.i("onRefresh","ok");
            cancelStatue(behavior,view);
        }

        @Override
        public void onLoadingProcess(float value, ImageBehavior behavior) {
            Log.i("onLoading",String.valueOf(value));
        }

        @Override
        public void onRefreshProcess(float value, ImageBehavior behavior) {
            Log.i("onRefresh",String.valueOf(value));
        }
    };


    private void cancelStatue(final ImageBehavior behavior,final View view){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                behavior.onComplete(view);
            }
        },3000);
    }
}
