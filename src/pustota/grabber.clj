(ns pustota.grabber
  (:use [clojure.core.async :only [chan go >!]]))

(defn grab
  "Создает workers потоков и возвращает канал."
  [workers parser]
  (let [c (chan)]
    (do
      (doseq [i (range workers)]
        (go
          (Thread/sleep (rand-int 1000))
          (>! c (parser i))))
      (println "Running threads: " workers)
      c)))





