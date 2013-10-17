(ns pustota.core-test
  (:require [clojure.test :refer :all]
            [pustota.core :refer :all]))

(deftest plus-test
  (testing "sum of 1 and 2"
    (is (= 3 (plus 1 2)))))

