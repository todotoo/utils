package onther;

/**
 * Created by eclipse3.2. function: User: FMD(����) Date: 2010-3-30 Time:
 * ����03:22:51 Email:fmdsaco99@163.com To change this template use File |
 * Settings | File Templates.
 */

public class Utils {

	public static String company = "金银房";
	public static String myemail = "fmdsaco@sina.com";
	public static String regkey = "，!!！,？？!!!,.。；‘“！·#￥%……—*（）——!%*）?%￥！”：／、"; // 对于内容结较的分割符
	public static long depthpercent = 70; // 网页内容比较
	public static float finishpercent = 0.2f;// 提交任务费用比例
	public static int checkfinish24job = -24;// 第二次检查时间
	public static int Notice = -(24 * 5);// 前几天的公告
	public static int deduct_payfee = 3;// 扣除费用时间 2表示结束时间的两天后
	public static int fraction_code = 1;// 发贴得分
	public static String dateformatH = "yyyy-MM-dd HH:00:00";
	public static boolean lock_create_job = true; // 生成任务锁
	public static boolean lock_check_job = true; // 检查任务锁
	public static String payfee_temporary_desc = "任务暂扣款"; // 暂扣款说明
	public static String alexaQueryUrl = "http://www.alexa.com/search?q=#-q-#&r=home_home&p=bigtop"; // Alexa排名查询网址
	public static String cryPto_spit = "_"; // 加密分隔符
	public static int pay_fee_state_topup = 1; // 1充值成功
	public static int pay_fee_state_apply = 2; // 2申请充值
	public static int pay_fee_state_temporary = 3;// 3暂扣款
	public static int pay_fee_state_finish = 4; // 4任务完成后扣款

	public static int channelForum = 1; // 论坛
	public static int channelAdvertorial = 2; // 软文
	public static int channelMicroBlog = 3; // 微博
	public static int channelBlog = 4; // 博客
	public static int ad_bidup = 5; // 广告竞价广告
	// 类型(1发贴2软文3微博4博客5广告)

	public static int AdvertorialPlan_date = 24 * 7; // 生成软文周期时间
	public static int AdvertorialPlan_audit = 7;// 结束后几天审核软文，在这之前的费用已结,检查作用
	public static float Advertorial_points = 0.15f; // 收取营客提交软文的手续费比例

	public static float ad_bidup_points = 0.2f;// 广告招标分成手续费用

	public static int task_provide_fee_state_no = 1;// 营客金额未发放
	public static int task_provide_fee_state_yes = 2;// 营客金额已发放

	public static int blog_settle_date = 10;// 博客最后结算东能超10天

	// -----------------
	public static int user_fraction_forum = 2;// 论坛每贴2分
	public static int user_fraction_ad = 20; // 发广告获得分数
	public static int user_fraction_blog = 5; // 发博客获得分数
	public static int user_fraction_advertorial = 10; // 发软文获得分数

	public static Integer master_email_templateid = 9;// 给营主每天发送邮件模板ID
	public static Integer master_guest_templateid = 8;// 给营客发送论坛营销的邮件模板ID
	public static Integer master_guest_everyDay_templateid = 7;// 给营客发送论坛营销的邮件模板ID
	public static Integer master_bidup_biduper_templateid = 6;// 营客给营主发送中标信息
	public static Integer master_bidup_masterTOguest_templateid = 5;// 营客给营主发送中标信息

}
