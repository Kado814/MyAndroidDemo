package com.myapplication.myandroiddemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.myapplication.myandroiddemo.R;
import com.myapplication.myandroiddemo.model.User;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.update.BmobUpdateAgent;

public class BmobActivity extends AppCompatActivity implements View.OnClickListener {

    private Button add_user;
    private Button check_user;
    private Button revise_user;
    private Button delect_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bmob.initialize(this, "6a01f0c126b88a38ca50d33c857a9596");
        setContentView(R.layout.activity_bmob);

        add_user = findViewById(R.id.add_user);
        check_user = findViewById(R.id.check_user);
        revise_user = findViewById(R.id.revise_user);
        delect_user = findViewById(R.id.delect_user);

        add_user.setOnClickListener(this);
        check_user.setOnClickListener(this);
        revise_user.setOnClickListener(this);
        delect_user.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_user:
                User user = new User();
                user.setEmail("240062654@qq.com");
                user.setMobilePhoneNumber("18682886132");
                user.setUsername("kkk");
                user.setPassword("123456");
                user.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if (e == null) {
                            Log.d("kkk", "添加数据成功，返回的objectId=" + s);
                        } else {
                            Log.e("kkk", "创建数据失败" + e.toString());
                        }
                    }
                });
                break;
            case R.id.check_user:
                BmobQuery<User> bmobQuery = new BmobQuery<>();
                bmobQuery.addWhereEqualTo("username", "kkk");
                bmobQuery.findObjects(new FindListener<User>() {
                    @Override
                    public void done(List<User> list, BmobException e) {
                        if (e == null) {
                            Log.d("kkk", "查询成功,kkk的email=" + list.get(0).getEmail());
                        } else {
                            Log.e("kkk", "查询失败" + e.toString());
                        }
                    }
                });
                break;
            case R.id.revise_user:
                //todo 暂时发现只能根据id操作
                User user1 = new User();
                user1.setMobilePhoneNumber("18682888888");
                user1.addUnique("username", "kkk");
                user1.update(new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e == null) {
                            Log.d("kkk", "修改成功,kkk的email");
                        } else {
                            Log.e("kkk", "修改失败" + e.toString());
                        }
                    }
                });
                break;
            case R.id.delect_user:
                break;
        }
    }
}
