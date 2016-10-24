package cn.edu.gdmec.s07150708.mycalculator;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
//计算按钮
    private Button button;
    //体重输入框
    private EditText editText;
    //男性按钮
    private RadioButton manCheckBox;
    //女性按钮
    private RadioButton womanCheckBox;
    //显示结果
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置页面布局
        setContentView(R.layout.activity_main);
        //获取对应的UI控件
        button=(Button) findViewById(R.id.calculate);
        editText=(EditText) findViewById(R.id.weight);
        manCheckBox=(RadioButton) findViewById(R.id.man);
        womanCheckBox=(RadioButton) findViewById(R.id.woman);
        textView=(TextView) findViewById(R.id.result);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //注册事件
        registerEvent();

    }
    private void registerEvent(){
        //注册按钮事件
       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               //判断是否已填写体重信息
               if (!editText.getText().toString().trim().equals("")) {
                   if (manCheckBox.isChecked()||womanCheckBox.isChecked()) {
                         double weight=Double.parseDouble(editText.getText().toString());
                       StringBuffer sb=new StringBuffer();
                       sb.append("----评估结果-----\n");
                       if (manCheckBox.isChecked()){
                           sb.append("男性的标准身高：");
                           //执行运算
                           double result=evaluateHeight(weight,"男");
                           sb.append((int)result+"(厘米)");
                       }
                       if (womanCheckBox.isChecked()){
                           sb.append("女性的标准身高：");
                           //执行运算
                           double result=evaluateHeight(weight,"女");
                           sb.append((int)result+"(厘米)");
                       }
                       //输出页面显示结果
                       textView.setText(sb.toString());
                   }else {
                       showMessage("请选择性别！");
                   }
               }else {
                   showMessage("请输入体重！");
               }
           }
           });
    }
    //处理执行代码
    private double evaluateHeight(double weight,String sex){
        double height;
        if(sex=="男"){
            height=170-(62-weight)/0.6;
        }else {
            height=158-(52-weight)/0.5;
        }
        return height;
    }
    //消息提示
    private void showMessage(String message){
        //提示框
        AlertDialog alertDialog=new AlertDialog.Builder(this).create();
        alertDialog.setTitle("系统消息");
        alertDialog.setMessage(message);
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE,"确定",new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.show();
    }
    //创建菜单
    public boolean onCreateOptionsMenu(Menu menu){
        menu.add(Menu.NONE,1,Menu.NONE,"退出");
        return super.onCreateOptionsMenu(menu);
    }
    //菜单事件
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case 1://退出
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
