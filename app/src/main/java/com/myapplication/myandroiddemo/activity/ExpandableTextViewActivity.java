package com.myapplication.myandroiddemo.activity;

import android.os.Bundle;

import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.myapplication.myandroiddemo.R;

public class ExpandableTextViewActivity extends MyBaseActivity {
    /**
     * Called when the activity is first created.
     */
    private ExpandableTextView expand_text_view;

    @Override
    protected int getLayoutResId() {
        return R.layout.expandabletextview;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        expand_text_view = (ExpandableTextView) findViewById(R.id.expand_text_view);

        expand_text_view.setText("自去年以来，美国已有21名消费者向CPSC报告称，他们购买的三星上开门式洗衣机发生爆炸或支离破碎，在德州及印第安纳州等地提起控告。之后，CPSC向部分三星洗衣机用户发出警告称，使用三星上开门式洗衣机的用户需要注意，出厂日期为2011年3月至2016年4月之间洗衣机产品存在安全问题。\n" +
                "　　此外，三星洗衣机在新西兰和澳大利亚传出多宗起火燃烧意外，已有六款型号约14万台洗衣机在澳洲范围内被召回。2013年，在澳大利亚就有近15万台存在火灾隐患的三星洗衣机被召回。据澳大利亚联合新闻社消息称，此前澳洲共发生了15起三星洗衣机引发的火灾事故。当时的原因与此次在美国发生的情况不同，主要是“顶盖因发热而烧融”以及“顶部灼烧并造成内部损坏”等。\n" +
                "　　三星表示，用户若以高速转动模式清洗床单或大件衣物时，将出现上述问题。三星美国分公司指，他们正与零售商及CPSC合作，通知消费者相关问题。消费者可选择让三星免费维修问题洗衣机，或是在购买新洗衣机时获部分退款，若消费者过去30日内曾购买出事型号洗衣机，可获全数退款。\n" +
                "　　此外，虽然加拿大无发生类似意外的报告，三星仍决定一并回收在加拿大出售的同款洗衣机。\n" +
                "　　此次事故让因处理手机退换业务焦头烂额的三星更加被动。三星Note 7爆炸事件以来，全球范围内绝大多数产品已经被召回。三星美国部门表示，在美国地区销售的Note 7手机已经召回了85%左右，但考虑到有些产品被用户带出国或者其他原因，最后一小部分将会较难召回。对此，他们将采取升级推送的方式，将电池电量限制在60%左右。这显示该系列问题手机的召回工作仍将耗费三星的精力。");
    }
}
