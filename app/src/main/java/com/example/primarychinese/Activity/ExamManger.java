package com.example.primarychinese.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.primarychinese.Bean.Exam;
import com.example.primarychinese.Bean.TextResource;
import com.example.primarychinese.DataSource.NetworkConfig;
import com.example.primarychinese.R;
import com.example.primarychinese.adpter.examRecyclerViewAdpter;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ExamManger extends AppCompatActivity {
    OkHttpClient client;
    ProgressDialog progressDialog ;
    List<Exam> ExamList;
    RecyclerView RexamList;
    examRecyclerViewAdpter adpter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_manger);
        RexamList = findViewById(R.id.examList);
        progressDialog  = new ProgressDialog(this);
        ExamList = new ArrayList<>();
        client = new OkHttpClient();
        // 创建并显示 ProgressDialog

        progressDialog.setMessage("请稍候...");
        progressDialog.setCancelable(false); // 设置是否可取消
        progressDialog.show();

        // 启动后台任务
        BackgroundTask backgroundTask = new BackgroundTask();
        backgroundTask.execute();

    }
    // 后台任务
    private class BackgroundTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            // 延时操作
            String url="http://"+ NetworkConfig.SERVER_ADDRESS+"/PrimaryChinese/SelectServlet";
            RequestBody formBody = new FormBody.Builder()
                    .add("method","SELECT_EXAM")
                    .build();
            Request request = new Request.Builder()
                    .url(url)
                    .post(formBody)
                    .build();



            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    Log.e("TAG", "onFailure: " + e );
                    //Toast.makeText(MainActivity.this, "RESPONCE_ERROR", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                    String jsonData = response.body().string();
                    Log.e("TAG",jsonData);
                    JSONArray jsonArray = JSON.parseArray(jsonData);
                    for (int i = 0; i < jsonArray.size(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        // 获取具体的字段值
                        String examName = jsonObject.getString("exam_name");
                        java.util.Date date= jsonObject.getDate("exam_date");


                        String examId = jsonObject.getString("exam_id");
                        Exam e=new Exam();
                        e.setExam_id(examId);
                        e.setExam_date(new Date(date.getTime()));
                        e.setExam_name(examName);
                        ExamList.add(e);
                    }


                }
            });
            Log.e("TAG","执行到了耗时操作的最后");
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            Log.e("TAG","耗时操作完成");
            adpter = new examRecyclerViewAdpter(ExamList);
            Log.e("TAG",String.valueOf(ExamList.size()));
            LinearLayoutManager layoutManager = new LinearLayoutManager(ExamManger.this);
            RexamList.setLayoutManager(layoutManager);
            RexamList.setAdapter(adpter);
            // 关闭 ProgressDialog
            progressDialog.dismiss();
        }
    }
}