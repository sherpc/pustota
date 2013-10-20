(ns pustota.grabber-test
  (:require [clojure.test :refer :all]
            [pustota.grabber :refer :all]
            [clojure.core.async :as async]))

;; (defn run-workers
;;   []
;;   (let [workers 4
;;         c (grab workers identity)]
;;        (reduce
;;         (fn [r _] (+ r (async/<!! c)))
;;         0
;;         (range workers))))

;; (deftest grab-test
;;   (testing "channel work"
;;     (is (= 6 (run-workers)))))


