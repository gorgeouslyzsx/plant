package com.example.plant.Adapter;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.plant.R;
import com.example.plant.bean.plantBean;
import com.example.plant.utils.Constant;

import java.io.IOException;
import java.util.List;


public class HomeAdapter extends BaseAdapter {
    private Context mContext;
    private List<plantBean> book;
    private Handler mHandler; // 添加Handler成员变量
    public HomeAdapter(Context context){
        this.mContext=context;
        mHandler = new Handler(Looper.getMainLooper()); // 初始化Handler
    }
    //数据更新
    public void setData(List<plantBean> book){
        this.book=book;
        notifyDataSetChanged();
    }
    //获取item总数
    @Override
    public int getCount() {
        return book==null?0:book.size();
    }
   //根据position得到对应Item对象
    @Override
    public bookBean getItem(int position) {
        return book==null?null:book.get(position);
    }
   //根据position得到对应Item的id
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if(convertView==null){
            vh=new ViewHolder();
            convertView=LayoutInflater.from(mContext).inflate(R.layout.book_item,null);
            vh.book_photo=(ImageView)convertView.findViewById(R.id.book_pic);
            vh.book_name=(TextView)convertView.findViewById(R.id.book_name);
            vh.book_author=(TextView)convertView.findViewById(R.id.book_author);
            vh.book_press=(TextView)convertView.findViewById(R.id.book_press);
            vh.book_price=(TextView)convertView.findViewById(R.id.book_price);
            vh.image_gwc=(ImageView) convertView.findViewById(R.id.image_gwc);
            convertView.setTag(vh);
        }
        else{
            vh=(ViewHolder)convertView.getTag();
        }
        final bookBean bean=getItem(position);
        if(bean!=null){
            vh.book_photo.setImageBitmap(bean.getbookphoto());
            vh.book_name.setText(bean.getbookname());
            vh.book_author.setText(bean.getbookauthor());
            vh.book_press.setText(bean.getbookpress());
            vh.book_price.setText(String.valueOf(bean.getbookprice()));
            vh.image_gwc.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(Constant.AddShopCart+"?username="+username
                                    +"&booknum="+bean.getbooknum())  // 设置请求的URL
                            .build();
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            e.printStackTrace();
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            if (response.isSuccessful()) {
                                String responseData = response.body().string();
                                if (responseData.equals("success")) {
                                    mHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(mContext, "添加成功，在购物车等亲!", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            } else {
                                Log.e("fail", "Request failed with code: " + response.code());
                            }
                        }
                    });

                }
            });
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bean == null) return;
                Intent intent = new Intent(mContext, BookDetail.class);
                //把图书的详细信息传递到图书详情界面
                intent.putExtra("book", bean);
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }
    class ViewHolder{
        public TextView book_name,book_author,book_press,book_price;
        public ImageView book_photo,image_gwc;
    }
}
