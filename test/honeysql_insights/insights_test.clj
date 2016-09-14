(ns honeysql-insights.insights-test
  (:require [honeysql-insights.format :refer :all]
            [honeysql-insights.helpers :refer :all]
            [honeysql.helpers :refer :all]
            [honeysql.core :as sql]
            [clojure.test :refer :all]))

(deftest select-test
  (testing "select one attribute"
    (is (= ["SELECT count(name)"]
           (-> {:select [:fn "count(*)"]}
               sql/format)))))

(deftest select-two-test
  (testing "select two attributes"
    (is (= ["SELECT count(name), average(duration)"]
           (-> {:select [(:count :name)
                         (:average :duration)]}
               sql/format)))))

(deftest select-from-test
  (testing "select count(name) from one event"
    (is (= ["SELECT count(name) FROM Transactions"]
           (-> {:select ["count(*)"]
                :from "Transactions"}
               sql/format)))))

(deftest select-from-test
  (testing "select count(name) from two events"
    (is (= ["SELECT count(name) FROM Transactions, PageView"]
           (-> (select :count)
               (from [:Transactions :PageView])
               sql/format)))))
