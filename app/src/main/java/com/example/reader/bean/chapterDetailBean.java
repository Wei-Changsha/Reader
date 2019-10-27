package com.example.reader.bean;

public class chapterDetailBean {
//    {
//        "ok": true,
//            "chapter": {
//        "title": ".",
//                "body": "在晋国都城新绛数里之外，耸立着一座夯土墙环绕的坚固小城，此城名为赵氏之宫，乃是晋国六大卿族之一，赵氏的私邑。\n这儿却还有一个流传更广的名字：下宫！七十多年前那场“下宫之难”，杀得人头滚滚、血灌井田，赵氏满门被灭，只幸存一个赵氏孤儿。随后赵氏孤儿绝境复起，这座被摧毁的城邑也恢复了些许元气，幸存的隶臣们都感慨这是先祖的恩德泽被。\n不过在邑中一处宽阔的马厩中，却有个赵氏少年对这所谓的“德泽”嗤之以鼻，他用没人听得到的声音嘀咕道：\n“京剧和电影里尽是胡编乱造，我来到了这时代，才知道，世上压根没有屠岸贾这个人啊！”\n“好奇害死猫啊，我就不该乱问自毁三观的，谁曾想到，剧本里的贞洁烈女赵庄姬，也就是我这具身体的太祖母。她居然，居然是个丈夫死后，就穿着丧服勾引叔叔上床的****。在奸情被撞破后，又作死向国君进谗言灭了家族满门，真是红颜祸水啊！”\n少年不住地摇头叹息，他尚未及冠，锥形发髻上只裹了条青色帻巾，上衣左衿紧紧压着右衿，在右腋下结缨，形成了华夏人崇尚的右衽模样。可他的下身，却随意地套着一条袴褶，这是从狄地传入的外来货，形似后世的裤子。这一结合，颇有些不伦不类，要是被赵氏之宫里那些死板的家师、家傅瞧见了，定然又是一顿口诛笔伐。\n他在充斥着牲畜气息的厩苑里，显得卓尔不群：虽容貌平平，但那双剑眉衬得一双眼睛格外精神；且眼窝微陷，鼻梁略高，似乎有部分戎狄血统；他手脚干净不像是干过重活的，脸色红润，牙齿整齐，显然是位衣食无忧的肉食者。却不知，为何跑到了这下贱肮脏的厩苑里？\n而且，他也不干活，就这么叼着根牧草，悠闲地坐在木质马槽上，
//    }
//    }

    private boolean ok;
    private Chapter chapter;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public static class Chapter{
        private String title;
        private String body;

        public Chapter(String title, String body) {
            this.title = title;
            this.body = body;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }
    }
}
