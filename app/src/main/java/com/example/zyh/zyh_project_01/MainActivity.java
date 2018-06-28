package com.example.zyh.zyh_project_01;

import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.example.zyh.zyh_project_01.ui.activity.BaseActivity;
import com.example.zyh.zyh_project_01.ui.activity.BaseFragmentActivity;
import com.example.zyh.zyh_project_01.ui.fragment.BookShelfFragment;
import com.example.zyh.zyh_project_01.utils.ZToast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseFragmentActivity {

    @BindView(R.id.btn_home)
    Button mHome;
    @BindView(R.id.btn_bookshelf)
    Button mBookShelf;
    @BindView(R.id.btn_mine)
    Button mMine;


    @Override
    protected void initView() {
        mHome.setBackgroundResource(R.drawable.homepage_press);
        fragments = new ArrayList<>();
        fragmentManager = getSupportFragmentManager();
        fragments.add(fragmentManager.findFragmentById(R.id.fm_home));
        fragments.add(fragmentManager.findFragmentById(R.id.fm_bookshelf));
        fragments.add(fragmentManager.findFragmentById(R.id.fm_mine));
        selectTab(0);
    }

    @OnClick(R.id.btn_home)
    public void toHomeFragment(View view){
        selectTab(0);
        resetBottomBtn();
        mHome.setBackgroundResource(R.drawable.homepage_press);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            initStatusBar(true);
        }
    }

    @OnClick(R.id.btn_bookshelf)
    public void toBookShelfFragment(View view){
        selectTab(1);
        resetBottomBtn();
        mBookShelf.setBackgroundResource(R.drawable.bookshelf_press);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            initStatusBar(true);
        }
    }

    @OnClick(R.id.btn_mine)
    public void toMineFragment(View view){
        selectTab(2);
        resetBottomBtn();
        mMine.setBackgroundResource(R.drawable.mine_press);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            initStatusBar(true);
        }
    }

    private void resetBottomBtn(){
        mHome.setBackgroundResource(R.drawable.homepage);
        mMine.setBackgroundResource(R.drawable.mine);
        mBookShelf.setBackgroundResource(R.drawable.bookshelf);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_base;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(ZToast.isShow()){
                return super.onKeyDown(keyCode,event);
            }else{
                ZToast.makeText(MainActivity.this,"再按一次返回键退出",1000).show();
                return false;
            }
        }else{
            return super.onKeyDown(keyCode, event);
        }

    }
}
