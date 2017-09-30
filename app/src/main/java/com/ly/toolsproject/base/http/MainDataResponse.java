package com.ly.toolsproject.base.http;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Administrator on 2017/4/17 0017.
 */

//主页数据返回
public class MainDataResponse {


    private List<ImagesBean> images;
    private List<AroundNew> around;
    private String category;
    private List<CategoryBean> categorys;
    private List<WeekendsBean> weekends;
    private List<GroupBean> group;
    private List<OurBean> our;
    private AdBean ad;
    private CardBean card;

    public CardBean getCard() {
        return card;
    }

    public void setCard(CardBean card) {
        this.card = card;
    }

    public List<CategoryBean> getCategorys() {
        return categorys;
    }

    public void setCategorys(List<CategoryBean> categorys) {
        this.categorys = categorys;
    }

    public List<GroupBean> getGroup() {
        return group;
    }

    public void setGroup(List<GroupBean> group) {
        this.group = group;
    }

    public AdBean getAd() {
        return ad;
    }

    public void setAd(AdBean ad) {
        this.ad = ad;
    }

    public List<ImagesBean> getImages() {
        return images;
    }

    public void setImages(List<ImagesBean> images) {
        this.images = images;
    }

    public List<AroundNew> getAround() {
        return around;
    }

    public void setAround(List<AroundNew> around) {
        this.around = around;
    }


    public List<WeekendsBean> getWeekends() {
        return weekends;
    }

    public void setWeekends(List<WeekendsBean> weekends) {
        this.weekends = weekends;
    }

    public List<OurBean> getOur() {
        return our;
    }

    public void setOur(List<OurBean> our) {
        this.our = our;
    }

    public static class CardBean {

        /**
         * id : 1
         * cover_image : /2017-09-13_59b8a71a3b9c0.jpg
         */

        private String id;
        private String cover_image;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCover_image() {
            return cover_image;
        }

        public void setCover_image(String cover_image) {
            this.cover_image = cover_image;
        }
    }

    public static class AdBean {


        /**
         * normal : {"id":"18","advertise_url":"0","activity_id":"165","advertise_img":"/2017-07-13_5966ea0bc00b6.png","advertise_name":"普通广告1","advertise_content":"","create_time":"1499916865","is_advertise":"1","update_time":"0","is_online":"1","advertise_type":"1"}
         * superBig : {"id":"19","advertise_url":"http://test.lebaoqinzi.com/Admin/Content/advertise/id/19.html","activity_id":"0","advertise_img":"/2017-07-13_5966ea7613c2f.png","advertise_name":"超大广告1","advertise_content":"<p>阿萨德撒 火娃<\/p>","create_time":"1499916927","is_advertise":"1","update_time":"0","is_online":"1","advertise_type":"2"}
         */

        private NormalBean normal;
        private SuperBigBean superBig;

        public NormalBean getNormal() {
            return normal;
        }

        public void setNormal(NormalBean normal) {
            this.normal = normal;
        }

        public SuperBigBean getSuperBig() {
            return superBig;
        }

        public void setSuperBig(SuperBigBean superBig) {
            this.superBig = superBig;
        }

        public static class NormalBean {
            /**
             * id : 18
             * advertise_url : 0
             * activity_id : 165
             * advertise_img : /2017-07-13_5966ea0bc00b6.png
             * advertise_name : 普通广告1
             * advertise_content :
             * create_time : 1499916865
             * is_advertise : 1
             * update_time : 0
             * is_online : 1
             * advertise_type : 1
             */

            private String id;
            private String advertise_url;
            private String activity_id;
            private String advertise_img;
            private String advertise_name;
            private String advertise_content;
            private String create_time;
            private String is_advertise;
            private String update_time;
            private String is_online;
            private String advertise_type;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getAdvertise_url() {
                return advertise_url;
            }

            public void setAdvertise_url(String advertise_url) {
                this.advertise_url = advertise_url;
            }

            public String getActivity_id() {
                return activity_id;
            }

            public void setActivity_id(String activity_id) {
                this.activity_id = activity_id;
            }

            public String getAdvertise_img() {
                return advertise_img;
            }

            public void setAdvertise_img(String advertise_img) {
                this.advertise_img = advertise_img;
            }

            public String getAdvertise_name() {
                return advertise_name;
            }

            public void setAdvertise_name(String advertise_name) {
                this.advertise_name = advertise_name;
            }

            public String getAdvertise_content() {
                return advertise_content;
            }

            public void setAdvertise_content(String advertise_content) {
                this.advertise_content = advertise_content;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getIs_advertise() {
                return is_advertise;
            }

            public void setIs_advertise(String is_advertise) {
                this.is_advertise = is_advertise;
            }

            public String getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(String update_time) {
                this.update_time = update_time;
            }

            public String getIs_online() {
                return is_online;
            }

            public void setIs_online(String is_online) {
                this.is_online = is_online;
            }

            public String getAdvertise_type() {
                return advertise_type;
            }

            public void setAdvertise_type(String advertise_type) {
                this.advertise_type = advertise_type;
            }
        }

        public static class SuperBigBean {
            /**
             * id : 19
             * advertise_url : http://test.lebaoqinzi.com/Admin/Content/advertise/id/19.html
             * activity_id : 0
             * advertise_img : /2017-07-13_5966ea7613c2f.png
             * advertise_name : 超大广告1
             * advertise_content : <p>阿萨德撒 火娃</p>
             * create_time : 1499916927
             * is_advertise : 1
             * update_time : 0
             * is_online : 1
             * advertise_type : 2
             */

            private String id;
            private String advertise_url;
            private String activity_id;
            private String advertise_img;
            private String advertise_name;
            private String advertise_content;
            private String create_time;
            private String is_advertise;
            private String update_time;
            private String is_online;
            private String advertise_type;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getAdvertise_url() {
                return advertise_url;
            }

            public void setAdvertise_url(String advertise_url) {
                this.advertise_url = advertise_url;
            }

            public String getActivity_id() {
                return activity_id;
            }

            public void setActivity_id(String activity_id) {
                this.activity_id = activity_id;
            }

            public String getAdvertise_img() {
                return advertise_img;
            }

            public void setAdvertise_img(String advertise_img) {
                this.advertise_img = advertise_img;
            }

            public String getAdvertise_name() {
                return advertise_name;
            }

            public void setAdvertise_name(String advertise_name) {
                this.advertise_name = advertise_name;
            }

            public String getAdvertise_content() {
                return advertise_content;
            }

            public void setAdvertise_content(String advertise_content) {
                this.advertise_content = advertise_content;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getIs_advertise() {
                return is_advertise;
            }

            public void setIs_advertise(String is_advertise) {
                this.is_advertise = is_advertise;
            }

            public String getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(String update_time) {
                this.update_time = update_time;
            }

            public String getIs_online() {
                return is_online;
            }

            public void setIs_online(String is_online) {
                this.is_online = is_online;
            }

            public String getAdvertise_type() {
                return advertise_type;
            }

            public void setAdvertise_type(String advertise_type) {
                this.advertise_type = advertise_type;
            }
        }
    }

    public static class ImagesBean {
        /**
         * id : 2
         * path : 20170405/asdsaf.jpg
         * redirect_url : www.google.cn
         */

        private String id;
        private String path;
        private String redirect_url;

        public ImagesBean(String id, String path, String redirect_url) {
            this.id = id;
            this.path = path;
            this.redirect_url = redirect_url;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getRedirect_url() {
            return redirect_url;
        }

        public void setRedirect_url(String redirect_url) {
            this.redirect_url = redirect_url;
        }
    }

    public static class ItemBean {
        private int type;
        private String act_type;
        private String id;
        private String title;
        private String category_name;
        private String image;
        private String amount;
        private double dis;
        private String person_number;

        public ItemBean() {
        }

        ItemBean(int type, String id, String act_type, String title, String category_name, String image, String amount, String person_number, double dis) {
            this.type = type;
            this.act_type = act_type;
            this.id = id;
            this.title = title;
            this.category_name = category_name;
            this.image = image;
            this.amount = amount;
            this.dis = dis;
            this.person_number = person_number;
        }

        public double getDis() {
            return dis;
        }

        public void setDis(double dis) {
            this.dis = dis;
        }


        public String getAct_type() {
            return act_type;
        }

        public void setAct_type(String act_type) {
            this.act_type = act_type;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCategory_name() {
            return category_name;
        }

        public void setCategory_name(String category_name) {
            this.category_name = category_name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getPerson_number() {
            return person_number;
        }

        public void setPerson_number(String person_number) {
            this.person_number = person_number;
        }
    }

    public static class AroundNew extends ItemBean {
        public AroundNew() {
            super.type = 1;
        }


        public AroundNew(String id, String act_type, String title, String category_name, String image, String amount, String person_number, double dis) {
            super(1, id, act_type, title, category_name, image, amount, person_number, dis);

        }

        public AroundNew(int type, String act_type, String id, String title, String category_name, String image, String amount, String person_number, double dis) {
            super(1, id, act_type, title, category_name, image, amount, person_number, dis);
        }


        /**
         * id : 1
         * title : dgdg
         * category_name :
         * image : 20170415/58f219812ce89.jpg
         * amount : 233.00
         * person_number : 0
         */


    }

    public static class WeekendsBean extends ItemBean {

        public WeekendsBean() {
            super.type = 1;
        }

        public WeekendsBean(String id, String act_type, String title, String category_name, String image, String amount, String person_number, double dis) {
            super(1, id, act_type, title, category_name, image, amount, person_number, dis);
        }

        public WeekendsBean(int type, String id, String act_type, String title, String category_name, String image, String amount, String person_number, double dis) {
            super(1, id, act_type, title, category_name, image, amount, person_number, dis);
        }

    }

    public static class GroupBean extends ItemBean {

        public GroupBean() {
            super.type = 1;
        }

        public GroupBean(String id, String act_type, String title, String category_name, String image, String amount, String person_number, double dis) {
            super(1, id, act_type, title, category_name, image, amount, person_number, dis);
        }

        public GroupBean(int type, String id, String act_type, String title, String category_name, String image, String amount, String person_number, double dis) {
            super(1, id, act_type, title, category_name, image, amount, person_number, dis);
        }
    }

    public static class OurBean extends ItemBean {
        public OurBean() {
            super.type = 2;
        }

        /**
         * id : 2
         * title : aisodj
         * category_name :
         * image :
         * amount : 333.00
         * person_number : 10
         */


        public OurBean(int type, String act_type, String id, String title, String category_name, String image, String amount, String person_number, double dis) {
            super(2, id, act_type, title, category_name, image, amount, person_number, dis);
        }

        public OurBean(String id, String act_type, String title, String category_name, String image, String amount, String person_number, double dis) {
            super(2, id, act_type, title, category_name, image, amount, person_number, dis);
        }
    }

    public static class CategoryBean implements Parcelable {


        /**
         * id : 34
         * name : 场馆
         * status : 1
         * cover_image : /2017-09-11_59b61ea801801.png
         * create_time : 1494989603
         * update_time : 1505107625
         * sort : 34
         * service_id : 26
         */

        private String id;
        private String name;
        private String status;
        private String cover_image;
        private String create_time;
        private String update_time;
        private String sort;
        private String service_id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCover_image() {
            return cover_image;
        }

        public void setCover_image(String cover_image) {
            this.cover_image = cover_image;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getService_id() {
            return service_id;
        }

        public void setService_id(String service_id) {
            this.service_id = service_id;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.name);
            dest.writeString(this.status);
            dest.writeString(this.cover_image);
            dest.writeString(this.create_time);
            dest.writeString(this.update_time);
            dest.writeString(this.sort);
            dest.writeString(this.service_id);
        }

        public CategoryBean() {
        }

        protected CategoryBean(Parcel in) {
            this.id = in.readString();
            this.name = in.readString();
            this.status = in.readString();
            this.cover_image = in.readString();
            this.create_time = in.readString();
            this.update_time = in.readString();
            this.sort = in.readString();
            this.service_id = in.readString();
        }

        public static final Creator<CategoryBean> CREATOR = new Creator<CategoryBean>() {
            @Override
            public CategoryBean createFromParcel(Parcel source) {
                return new CategoryBean(source);
            }

            @Override
            public CategoryBean[] newArray(int size) {
                return new CategoryBean[size];
            }
        };
    }

}
