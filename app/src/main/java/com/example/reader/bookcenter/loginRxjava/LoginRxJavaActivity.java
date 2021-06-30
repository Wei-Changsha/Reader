package com.example.reader.bookcenter.loginRxjava;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.reader.MainActivity;
import com.example.reader.R;
import com.example.reader.bean.User;

import org.litepal.crud.DataSupport;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;



public class LoginRxJavaActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText accountEdit;
    private EditText passwordEdit;
    private Button loginBtn;
    private Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

    }

    private void initView(){
        accountEdit=findViewById(R.id.account);
        passwordEdit=findViewById(R.id.password);

        loginBtn=findViewById(R.id.login);
        registerBtn=findViewById(R.id.register);

        loginBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:{

                Observable.create(new ObservableOnSubscribe<User>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<User> emitter) {
                        String account = accountEdit.getText().toString();
                        String password = passwordEdit.getText().toString();
                        User user = new User(account, password);
                        emitter.onNext(user);
                        emitter.onComplete();
                    }
                })
                        .map(new Function<User, Boolean>() {
                            @Override
                            public Boolean apply(User user) {
                                List<User> users= DataSupport.findAll(User.class);
                                for (User u:users){
                                    if (u.getAccount().equals(user.getAccount()) && u.getPassword().equals(user.getPassword())){
                                        return false;//找到已经注册过的账户
                                    }
                                }
                                return true;//没有找到已经注册过的账户
                            }
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Boolean>() {
                            @Override
                            public void accept(Boolean aBoolean) {
                                if(aBoolean) {
                                    Toast.makeText(LoginRxJavaActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(LoginRxJavaActivity.this, MainActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(LoginRxJavaActivity.this,"该账户已被注册过，请重新注册",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
           
                break;
            }
            case R.id.login:{

                Observable.create(new ObservableOnSubscribe<User>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<User> emitter) {
                        String account = accountEdit.getText().toString();
                        String password = passwordEdit.getText().toString();
                        User user = new User(account, password);
                        emitter.onNext(user);
                        emitter.onComplete();
                    }
                })
                        .map(new Function<User, Boolean>() {//检查账户和密码是否已经注册
                            @Override
                            public Boolean apply(User user) {
                                checkDataValid(user.getAccount(), user.getPassword());
                                List<User> loginList=DataSupport.where("account=?",user.getAccount()).find(User.class);
                                if (loginList.isEmpty()){
                                    //Toast.makeText(LoginRxJavaActivity.this,"用户未注册",Toast.LENGTH_SHORT).show();
                                    return false;
                                }else if(loginList.get(0).getPassword().equals(user.getPassword())){
                                    return true;
                                }else{
                                    return false;
                                }
                            }
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Boolean>() {
                            @Override
                            public void accept(Boolean aBoolean) {
                                if(aBoolean) {
                                    //tiaozhuan
                                    Intent intent=new Intent(LoginRxJavaActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    Toast.makeText(LoginRxJavaActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(LoginRxJavaActivity.this,"密码错误",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


                break;
            }
            default:
                break;
        }
    }

    //检查账号或者密码是否为空
    private String checkDataValid(String account,String password){
        if(TextUtils.isEmpty(account)|TextUtils.isEmpty(password)) {
            return "账号或者密码不能为空";
        }
        return "";
    }
}
