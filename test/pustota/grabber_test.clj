(ns pustota.grabber-test
  (:require [clojure.test :refer :all]
            [pustota.grabber :refer :all]
            [clojure.core.async :as async]))

(defn reader
  [result c]
  (async/<!!
    (async/go
      (let [x (async/<! c)]
        (println "*************** read from c: " x)
        + result x))))

(defn run-workers
  []
  (let [workers 4
        c (grab workers identity)]
    (async/<!!
     (async/go
      (+ (async/<! c) (async/<! c) (async/<! c) (async/<! c))))))

;;      (reduce
;;       (fn [result _] (reader result grabber))
;;       0
;;       (range workers)))))

(deftest grab-test
  (testing "channel work"
    (is (= 6 (run-workers)))))



