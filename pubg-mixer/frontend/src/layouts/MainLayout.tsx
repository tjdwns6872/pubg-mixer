import { Outlet } from "react-router-dom"
import Header from "../components/layout/Header"
import Footer from "../components/layout/Footer"

export default function MainLayout() {
  return (
    <div className="flex flex-col min-h-screen bg-slate-900 text-white">

      <Header />

      <main className="flex-1 px-6 py-6">
        <Outlet />
      </main>

      <Footer />

    </div>
  )
}