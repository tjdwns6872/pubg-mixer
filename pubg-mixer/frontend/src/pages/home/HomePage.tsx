import CenterLayout from "../../layouts/CenterLayout"
import MenuButton from "../../components/ui/MenuButton"

export default function HomePage() {
  return (
    <CenterLayout>

      <div className="text-center w-full max-w-sm">

        {/* Title */}

        <h1 className="text-4xl md:text-5xl font-bold text-white mb-10">
          PUBG Mixer
        </h1>

        {/* Menu */}

        <div className="flex flex-col gap-4">

          <MenuButton text="매칭 시작" />

          <MenuButton text="설정" />

        </div>

      </div>

    </CenterLayout>
  )
}