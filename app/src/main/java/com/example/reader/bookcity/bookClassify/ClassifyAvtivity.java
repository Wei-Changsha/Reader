package com.example.reader.bookcity.bookClassify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.reader.R;

public class ClassifyAvtivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classify_avtivity);
        TextView textGuyan=findViewById(R.id.nv_gu_yan);
        textGuyan.setOnClickListener(this);
        TextView textXianyan=findViewById(R.id.nv_xian_yan);
        textXianyan.setOnClickListener(this);
        TextView textXiaoyuan=findViewById(R.id.nv_xiao_yuan);
        textXiaoyuan.setOnClickListener(this);
        TextView textChunai=findViewById(R.id.nv_chun_ai);
        textChunai.setOnClickListener(this);
        TextView textXuanhuan=findViewById(R.id.nv_xuan_huan);
        textXuanhuan.setOnClickListener(this);
        TextView textXianxia=findViewById(R.id.nv_xian_xia);
        textXianxia.setOnClickListener(this);
        TextView textKehuan=findViewById(R.id.nv_ke_huan);
        textKehuan.setOnClickListener(this);
        TextView textYouxi=findViewById(R.id.nv_you_xi);
        textYouxi.setOnClickListener(this);
        TextView textXuanyi=findViewById(R.id.nv_xuan_yi);
        textXuanyi.setOnClickListener(this);
        TextView textTongren=findViewById(R.id.nv_tong_ren);
        textTongren.setOnClickListener(this);
        TextView textNvzun=findViewById(R.id.nv_zun);
        textNvzun.setOnClickListener(this);
        TextView textGaoxiao=findViewById(R.id.nv_gao_xiao);
        textGaoxiao.setOnClickListener(this);

    }


    @Override
    public void onClick(View view){
        Intent intent=null;
        String  address;
        switch (view.getId()){
            case R.id.nv_gu_yan:{
                Log.d("ClassifyActivity","aaa nvguyuan0");
                //跳转到分类列表
                address="http://api.zhuishushenqi.com/book/by-categories?gender=female&type=hot&major=古代言情&minor=穿越时空&start=0&limit=20";
                intent=new Intent(ClassifyAvtivity.this,ClassifyListActivity.class);
                intent.putExtra("address",address);
                startActivity(intent);
                Log.d("ClassifyActivity","aaa nvguyuan1");
                break;
            }
            case R.id.nv_xian_yan:{
                address="http://api.zhuishushenqi.com/book/by-categories?gender=female&type=hot&major=现代言情&minor=豪门总裁&start=0&limit=20";
                intent=new Intent(ClassifyAvtivity.this,ClassifyListActivity.class);
                intent.putExtra("address",address);
                startActivity(intent);
                break;
            }
            case R.id.nv_xiao_yuan:{
                address="http://api.zhuishushenqi.com/book/by-categories?gender=female&type=hot&major=青春校园&start=0&limit=20";
                intent=new Intent(ClassifyAvtivity.this,ClassifyListActivity.class);
                intent.putExtra("address",address);
                startActivity(intent);
                break;
            }
            case R.id.nv_chun_ai:{
                address="http://api.zhuishushenqi.com/book/by-categories?gender=female&type=hot&major=纯爱&minor=现代纯爱&start=0&limit=20";
                intent=new Intent(ClassifyAvtivity.this,ClassifyListActivity.class);
                intent.putExtra("address",address);
                startActivity(intent);
                break;
            }
            case R.id.nv_xuan_huan:{
                address="http://api.zhuishushenqi.com/book/by-categories?gender=female&type=hot&major=玄幻奇幻minor=玄幻异世&start=0&limit=20";
                intent=new Intent(ClassifyAvtivity.this,ClassifyListActivity.class);
                intent.putExtra("address",address);
                startActivity(intent);
                break;
            }
            case R.id.nv_xian_xia:{
                address="http://api.zhuishushenqi.com/book/by-categories?gender=female&type=hot&major=武侠仙侠&minor=武侠&start=0&limit=20";
                intent=new Intent(ClassifyAvtivity.this,ClassifyListActivity.class);
                intent.putExtra("address",address);
                startActivity(intent);
                break;
            }
            case R.id.nv_ke_huan:{
                address="http://api.zhuishushenqi.com/book/by-categories?gender=female&type=hot&major=科幻&start=0&limit=20";
                intent=new Intent(ClassifyAvtivity.this,ClassifyListActivity.class);
                intent.putExtra("address",address);
                startActivity(intent);
                break;
            }
            case R.id.nv_you_xi:{
                address="http://api.zhuishushenqi.com/book/by-categories?gender=female&type=hot&major=游戏竞技&start=0&limit=20";
                intent=new Intent(ClassifyAvtivity.this,ClassifyListActivity.class);
                intent.putExtra("address",address);
                startActivity(intent);
                break;
            }
            case R.id.nv_xuan_yi:{
                address="http://api.zhuishushenqi.com/book/by-categories?gender=female&type=hot&major=悬疑灵异&minor=悬疑&start=0&limit=20";
                intent=new Intent(ClassifyAvtivity.this,ClassifyListActivity.class);
                intent.putExtra("address",address);
                startActivity(intent);
                break;
            }
            case R.id.nv_tong_ren:{
                address="http://api.zhuishushenqi.com/book/by-categories?gender=female&type=hot&major=同人&minor=游戏同人&start=0&limit=20";
                intent=new Intent(ClassifyAvtivity.this,ClassifyListActivity.class);
                intent.putExtra("address",address);
                startActivity(intent);
                break;
            }
            case R.id.nv_zun:{
                address="http://api.zhuishushenqi.com/book/by-categories?gender=female&type=hot&major=女尊&start=0&limit=20";
                intent=new Intent(ClassifyAvtivity.this,ClassifyListActivity.class);
                intent.putExtra("address",address);
                startActivity(intent);
                break;
            }
            case R.id.nv_gao_xiao:{
                address="http://api.zhuishushenqi.com/book/by-categories?gender=female&type=hot&major=搞笑&start=0&limit=20";
                intent=new Intent(ClassifyAvtivity.this,ClassifyListActivity.class);
                intent.putExtra("address",address);
                startActivity(intent);
                break;
            }


        }

    }


}
