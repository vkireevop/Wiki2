package com.example.wiki2.Models;

    public class City {
        private String cityLabel;
        private String population;

        public String getCityLabel() {
            return cityLabel;
        }

        @Override
        public String toString() {
            return "City{" +
                    "cityLabel='" + cityLabel + '\'' +
                    ", population='" + population + '\'' +
                    '}';
        }

        public City() {
        }

        public void setCityLabel(String cityLabel) {
            this.cityLabel = cityLabel;
        }

        public String getPopulation() {
            return population;
        }

        public void setPopulation(String population) {
            this.population = population;
        }

        public City(String cityLabel, String population) {
            this.cityLabel = cityLabel;
            this.population = population;
        }
    }


