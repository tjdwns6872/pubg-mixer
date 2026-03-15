import { BrowserRouter, Routes, Route } from "react-router-dom"

import HomePage from "../pages/home/HomePage"

import MainLayout from "../layouts/MainLayout"

export default function Router() {
  return (
    <BrowserRouter>

      <Routes>

        {/* 메인 화면 */}
        <Route path="/" element={<HomePage />} />

        {/* 헤더/푸터 적용 영역 */}
        <Route element={<MainLayout />}>

        </Route>

      </Routes>

    </BrowserRouter>
  )
}