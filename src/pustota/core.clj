(ns pustota.core
  (:use [clojure.tools.cli :only [cli]])
  (:require [pustota.grabber :as g]
            [clojure.core.async :as async])
  (:gen-class))



(defn parse-args
  "Парсит аргументы командной строки по заданному словарю."
  [args]
  (cli args
       ["-w" "--workers" "Количество потоков" :parse-fn #(Integer. %) :default 2]
       ["-r" "--report-period" "Частота отчетов (сек)" :parse-fn #(Integer. %) :default 10]
       ["-d" "--dup-to-stop" "Количество дубликатов для остановки" :parse-fn #(Integer. %) :default 500]
       ["-hf" "--hash-file" "Файл хешей" :default "hash.bin" ]
       ["-qf" "--quotes-file" "Файл записей" :default "quotes.txt" ]
       ["-h" "--help" "Show help" :default false :flag true]))


(defn -main
  [& args]
  (let [[options args banner] (parse-args args)]
    (when (:help options)
      (println banner)
      (System/exit 0))
    (let [workers (:workers options)
          grabber (g/grab workers identity)]
      (async/<!!
        (async/go
          (doseq [_ (range workers)]
            (println (async/<! grabber))))))))


