package com.school.twohand.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.school.twohand.entity.Goods;
import com.school.twohand.query.entity.QueryGoodsBean;
import com.school.twohand.schooltwohandapp.R;
import com.school.twohand.utils.CommonAdapter;
import com.school.twohand.utils.NetUtil;
import com.school.twohand.utils.ViewHolder;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class GoodsClassDetailActivity extends AppCompatActivity {

    @InjectView(R.id.goodsList)
    ListView goodsList;//显示商品信息的listView
    //定位的学校
    String userSchoolName;
    //分类id
    String classId;
    List<Goods> goodsMessage = new ArrayList<>();//服务器获取到的数据源
    String sousuo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_class_detail);
        ButterKnife.inject(this);
        classId = getIntent().getStringExtra("result");
        sousuo = getIntent().getStringExtra("sousuo");
        initListBody();
    }

    public void initListBody() {
        final RequestParams requestParams = new RequestParams(NetUtil.url + "QueryGoodsServlet");
        QueryGoodsBean queryGoodsBean = null;
        if (sousuo != null && classId == null) {
            queryGoodsBean = new QueryGoodsBean(sousuo, userSchoolName, null, 0, null, null);
        } else if (sousuo == null && classId != null) {
            queryGoodsBean = new QueryGoodsBean(null, userSchoolName, classId, 0, null, null);
        }

        Gson gson = new Gson();
        String queryGoodsBeanString = gson.toJson(queryGoodsBean);
        requestParams.addQueryStringParameter("queryGoodsBean", queryGoodsBeanString);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(final String result) {
                final Gson gson = new Gson();
                goodsMessage = gson.fromJson(result, new TypeToken<List<Goods>>() {
                }.getType());

                //用通用适配器将数据源显示在listView上
                CommonAdapter<Goods> co = new CommonAdapter<Goods>(GoodsClassDetailActivity.this, goodsMessage, R.layout.goods_message) {
                    @Override
                    public void convert(ViewHolder viewHolder, Goods goods, final int position) {
                        ImageView userHeadView = viewHolder.getViewById(R.id.user_head_t);//用户头像
                        TextView userName = viewHolder.getViewById(R.id.user_name_t);//用户名
                        TextView goodsPrice = viewHolder.getViewById(R.id.goods_price_t);//商品价格
                        //ImageView goodsImage = viewHolder.getViewById(R.id.goods_image_t);//商品图片
                        TextView userSchool = viewHolder.getViewById(R.id.user_school_t);//用户学校
                        TextView amoyCircle = viewHolder.getViewById(R.id.amoy_circle_t);//淘圈名
                        TextView like = viewHolder.getViewById(R.id.like_t);//点赞
                        TextView messageBoard = viewHolder.getViewById(R.id.message_t);//留言
                        TextView goodsText = viewHolder.getViewById(R.id.goods_text_t);//商品描述
                        //从数据库获取头像
                        String userHeadUrl = NetUtil.imageUrl + goods.getGoodsUser().getUserHead();
                        ImageOptions userImageOptions = new ImageOptions.Builder()
                                .setCircular(true)
                                .build();
                        x.image().bind(userHeadView, userHeadUrl, userImageOptions);

                        //从数据库获取商品图片
                        /**
                         * 修改为多图片滑动滑到最后一张的时候可以进入详情或者点击进入详情
                         */
//                        String goodsUrl = NetUtil.imageUrl+goods.getGoodsImages().get(0).getImageAddress();
//                        ImageOptions goodsImageOptions= new ImageOptions.Builder()
//                                .build();
//                        x.image().bind(goodsImage,goodsUrl,goodsImageOptions);
                        LinearLayout LL = viewHolder.getViewById(R.id.LL);
                        LL.setTag(goods);
                        LL.removeAllViews(); //加之前要先把之前的remove掉，！！！
                        for (int i = 0; i < ((Goods) LL.getTag()).getGoodsImages().size(); i++) {
                            View view = LayoutInflater.from(GoodsClassDetailActivity.this).inflate(
                                    R.layout.each_taoquan_image_item, null);
                            ImageView iv_goodsImage = (ImageView) view.findViewById(R.id.iv_goods_image_item);
                            iv_goodsImage.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(GoodsClassDetailActivity.this, DetailGoodsActivity.class);
                                    intent.putExtra("goodsMessage", gson.toJson(goodsMessage));
                                    intent.putExtra("position", position + 1);
                                    startActivity(intent);
                                }
                            });
                            String url = NetUtil.imageUrl + ((Goods) LL.getTag()).getGoodsImages().get(i).getImageAddress();
                            ImageOptions imageOptions2 = new ImageOptions.Builder()
                                    .setFailureDrawableId(R.mipmap.ic_launcher)
                                    .setLoadingDrawableId(R.mipmap.ic_launcher)
                                    .setCrop(true).build();
                            x.image().bind(iv_goodsImage, url, imageOptions2);
                            LL.addView(view);
                        }

                        //给控件赋值
                        userName.setText(goods.getGoodsUser().getUserName());
                        goodsPrice.setText("￥" + goods.getGoodsPrice() + "");

                        userSchool.setVisibility(View.GONE);
                        if (goods.getGoodsUserSchoolName() != null) {
                            userSchool.setVisibility(View.VISIBLE);
                            userSchool.setText("来自 " + goods.getGoodsUserSchoolName());
                        } else {
                            if (goods.getGoodsUser().getUserSchoolName() != null) {
                                userSchool.setVisibility(View.VISIBLE);
                                userSchool.setText("来自 " + goods.getGoodsUser().getUserSchoolName());
                            }
                        }
                        amoyCircle.setVisibility(View.GONE);
                        if (goods.getGoodsAmoyCircle() != null) {
                            amoyCircle.setVisibility(View.VISIBLE);
                            amoyCircle.setText("淘圈丨" + goods.getGoodsAmoyCircle().getCircleName());
                        }
                        like.setText("点赞" + goods.getGoodsLikes().size());
                        messageBoard.setText("留言" + goods.getGoodsMessageBoards().size());
                        goodsText.setText(goods.getGoodsTitle() + "  " + goods.getGoodsDescribe());

                        LinearLayout LL_bottom = viewHolder.getViewById(R.id.LL_bottom);
                        LL_bottom.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(GoodsClassDetailActivity.this, DetailGoodsActivity.class);
                                intent.putExtra("goodsMessage", gson.toJson(goodsMessage));
                                intent.putExtra("position", position + 1);  //这里要加1，否则数据会错乱？？？
                                startActivity(intent);
                            }
                        });
                    }
                };
                goodsList.setAdapter(co);
                //点击跳转详情界面
//                goodsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        Intent intent = new Intent(GoodsClassDetailActivity.this,DetailGoodsActivity.class);
//                        intent.putExtra("goodsMessage",result);
//                        intent.putExtra("position",position+1);
//                        startActivity(intent);
//                    }
//                });
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }


    @OnClick(R.id.iv_classify_return)
    public void onClick() {
        finish();
    }



}
