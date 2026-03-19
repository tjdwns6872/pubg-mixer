import CenterLayout from "../../layouts/CenterLayout"
import MenuButton from "../../components/ui/MenuButton"

export default function HomePage() {
  return (
    <CenterLayout>

      <div className="flex w-full max-w-xs flex-col gap-3 sm:max-w-sm md:max-w-md px-2 sm:px-0 mx-auto items-stretch">
        <MenuButton variant="primary" text="매칭 시작" />
        <MenuButton variant="secondary" text="설정" />
        <MenuButton variant="secondary" text="사용법" />
      </div>

    </CenterLayout>
  )
}