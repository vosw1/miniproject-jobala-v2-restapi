package com.example.jobala.scrap;

import lombok.Data;

public class ScrapResponse {
    @Data
    public static class CompDTO {
        private Integer id;
        private Integer userId;
        private Integer resumeId;

        public CompDTO(Scrap scrap) {
            this.id = scrap.getId();
            this.userId = scrap.getUser().getId();
            this.resumeId = scrap.getResume().getId();
        }
    }

    @Data
    public static class GuestDTO {
        private Integer id;
        private Integer userId;
        private Integer jobopenId;

        public GuestDTO(Scrap scrap) {
            this.id = scrap.getId();
            this.userId = scrap.getUser().getId();
            this.jobopenId = scrap.getJobopen().getId();
        }
    }
}
