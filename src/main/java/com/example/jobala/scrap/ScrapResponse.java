package com.example.jobala.scrap;

import lombok.Data;

public class ScrapResponse {

    @Data
    public static class CompDTO {
        private Integer scrapId;
        private Integer userId;
        private Integer resumeId;

        public CompDTO(Scrap scrap) {
            this.scrapId = scrap.getId();
            this.userId = scrap.getUser().getId();
            this.resumeId = scrap.getResume().getId();
        }
    }

    @Data
    public static class GuestDTO {
        private Integer srapId;
        private Integer userId;
        private Integer jobopenId;

        public GuestDTO(Scrap scrap) {
            this.srapId = scrap.getId();
            this.userId = scrap.getUser().getId();
            this.jobopenId = scrap.getJobopen().getId();
        }
    }
}
