package com.cmm.worldartapk.bean;

import java.util.List;

/**
 * 搜索JavaBean
 * Created by Administrator on 2015/12/17.
 */
public class SearchBean {
    public List<SearchData> data;
    public String success;
    public String limit;
    public String offset;

    public class SearchData {
        public String exhibition_city;
        public String exhibition_city_cover;
        public String exhibition_country;
        public String exhibition_cover;
        public String exhibition_ent_time;
        public String exhibition_id;
        public String exhibition_intro;
        public String exhibition_location;
        public String exhibition_open_time;
        public String exhibition_province;
        public String exhibition_ref_story_id;
        public String exhibition_sequence;
        public String exhibition_start_time;
        public String exhibition_title;
        public List<ExhibitionImage> exhibition_image_array;

        public class ExhibitionImage {
            public String image_url;
            public String material_id;
            public String resource_id;
            public ResourceDescription resource_description;

            public class ResourceDescription {
                public String CoverCropRect;
                public String CoverCropRectRate;
                public String CoverDescriptionAlignment;
                public String CoverDescriptionFontIsBold;
                public String CoverDescription;
                public String CoverDisplayWidthRate;
                public String CoverSize;
                public String type;
            }
        }
    }
}
