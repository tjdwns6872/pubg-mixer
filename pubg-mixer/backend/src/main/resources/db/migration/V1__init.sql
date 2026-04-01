INSERT INTO `map` (`map_name_internal`, `map_name_display`, `map_size_km`, `image_path`, `max_coordinate`) VALUES
-- 8x8 km 대형 맵
('Erangel_Main', '에란겔', 8, '/assets/maps/erangel.jpg', 816000.0),
('Desert_Main', '미라마', 8, '/assets/maps/miramar.jpg', 816000.0),
('Tiger_Main', '태이고', 8, '/assets/maps/taego.jpg', 816000.0),
('Kiki_Main', '데스턴', 8, '/assets/maps/deston.jpg', 816000.0),
('Neon_Main', '론도', 8, '/assets/maps/rondo.jpg', 816000.0),
-- 6x6 km 중형 맵
('DihorOtok_Main', '비켄디', 6, '/assets/maps/vikendi.jpg', 612000.0),
-- 4x4 km 및 소형 맵
('Savage_Main', '사녹', 4, '/assets/maps/sanhok.jpg', 408000.0);

INSERT INTO `landmark` (`map_id`, `name`, `x_coord`, `y_coord`, `radius`) VALUES
-- 주요 대도시 (반지름 10,000~15,000)
(1, 'Pochinki', 416000, 395000, 15000.0),
(1, 'Georgopol North', 185000, 160000, 12000.0),
(1, 'Georgopol South', 185000, 245000, 12000.0),
(1, 'Yasnaya Polyana', 590000, 245000, 15000.0),
(1, 'Sosnovka Military Base', 425000, 725000, 18000.0),
(1, 'Novorepnoye', 625000, 735000, 12000.0),
(1, 'Mylta', 625000, 495000, 10000.0),
(1, 'Rozhok', 415000, 285000, 8000.0),
(1, 'Severny', 425000, 85000, 10000.0),
(1, 'Primorsk', 155000, 735000, 10000.0),
-- 주요 거점 및 시설 (반지름 5,000~8,000)
(1, 'School', 455000, 325000, 8000.0),
(1, 'Hospital', 135000, 285000, 8000.0),
(1, 'Mylta Power', 765000, 445000, 10000.0),
(1, 'Lipovka', 765000, 285000, 8000.0),
(1, 'Stalber', 665000, 105000, 8000.0),
(1, 'Quarry', 155000, 495000, 9000.0),
(1, 'Prison', 645000, 345000, 7000.0),
(1, 'Mansion', 655000, 285000, 6000.0),
(1, 'Shelter', 675000, 425000, 5000.0),
(1, 'Ruins', 265000, 325000, 7000.0),
(1, 'Shooting Range', 335000, 135000, 7000.0),
(1, 'Ferry Pier', 285000, 625000, 7000.0),
(1, 'Farm', 485000, 505000, 8000.0),
(1, 'Gatka', 285000, 415000, 7000.0),
(1, 'Zharki', 85000, 85000, 7000.0),
(1, 'Kameshki', 785000, 125000, 6000.0);

